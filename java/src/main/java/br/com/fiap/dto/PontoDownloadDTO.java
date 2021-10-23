package br.com.fiap.dto;

import br.com.fiap.model.Ponto;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class PontoDownloadDTO {

    private Long id;
    private Instant marcacao;
    private String justificativa;
    private Instant dataCriacao;
    private Instant dataAlteracao;

    public PontoDownloadDTO(Ponto ponto) {
        BeanUtils.copyProperties(ponto, this);
    }

    public static List<PontoDownloadDTO> parse(List<Ponto> pontos) {
        return pontos.stream().map(PontoDownloadDTO::new).collect(Collectors.toList());
    }

}
