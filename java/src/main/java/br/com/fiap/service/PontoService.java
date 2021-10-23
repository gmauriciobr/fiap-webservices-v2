package br.com.fiap.service;

import br.com.fiap.dto.AlteraPontoDTO;
import br.com.fiap.dto.JustificaPontoDTO;
import br.com.fiap.dto.PontoDownloadDTO;
import br.com.fiap.exception.ApiException;
import br.com.fiap.model.Ponto;
import br.com.fiap.model.Usuario;
import br.com.fiap.repository.PontoRepository;
import br.com.fiap.util.SecurityUtils;
import com.opencsv.CSVWriter;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvException;
import java.io.PrintWriter;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class PontoService {

    private final PontoRepository pontoRepository;
    private EntityManager em;

    public Ponto buscaPorId(Long id) {
        return pontoRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ("Ponto não encontrado")));
    }

    public Ponto ponto() {
        var ponto = new Ponto();
        ponto.setMarcacao(Instant.now());
        return salvar(ponto);
    }

    public List<Ponto> pontos(LocalDate dataInicio, LocalDate dataFim) {
        return pontoRepository
            .findByUsuarioIdAndMarcacaoBetween(
                SecurityUtils.getUsuarioLogado().getId(),
                dataInicio.atTime(LocalTime.MIN).atZone(ZoneId.systemDefault()).toInstant(),
                dataFim.atTime(LocalTime.MAX).atZone(ZoneId.systemDefault()).toInstant());
    }

    public Ponto alteraPonto(Long id, AlteraPontoDTO dto) {
        var ponto = buscaPorId(id);
        BeanUtils.copyProperties(dto, ponto);
        return pontoRepository.save(ponto);
    }

    public Ponto justifica(JustificaPontoDTO dto) {
        var ponto = JustificaPontoDTO.parse(dto);
        return salvar(ponto);
    }

    public Ponto salvar(Ponto ponto) {
        ponto.setUsuario(new Usuario(SecurityUtils.getUsuarioLogado().getId()));
        return pontoRepository.save(ponto);
    }

    public void download(LocalDate dataInicio, LocalDate dataFim, PrintWriter writer) {
        var list = PontoDownloadDTO.parse(pontos(dataInicio, dataFim));
        try {
            HeaderColumnNameMappingStrategy<PontoDownloadDTO> mappingStrategy = new HeaderColumnNameMappingStrategy<>();
            mappingStrategy.setType(PontoDownloadDTO.class);
            StatefulBeanToCsv<PontoDownloadDTO> beanToCsv = new StatefulBeanToCsvBuilder<PontoDownloadDTO>(writer)
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .withSeparator(';')
                .withMappingStrategy(mappingStrategy)
                .build();
            beanToCsv.write(list);
            writer.close();
        } catch (CsvException ex) {
            throw new ApiException("Erro ao gerar CSV");
        }
    }
}
