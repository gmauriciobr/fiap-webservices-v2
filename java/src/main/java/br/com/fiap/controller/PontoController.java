package br.com.fiap.controller;

import br.com.fiap.dto.AlteraPontoDTO;
import br.com.fiap.dto.JustificaPontoDTO;
import br.com.fiap.service.PontoService;
import br.com.fiap.view.PontoViewModel;
import br.com.fiap.view.PontosViewModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@RestController
@RequestMapping("/ponto")
@RequiredArgsConstructor
@Tag(name = "ponto", description = "Serviço para administração do ponto")
public class PontoController {

    private final PontoService pontoService;

    @PostMapping
    @Operation(summary = "Marca ponto hora atual", tags = {"ponto"})
    public ResponseEntity<PontoViewModel> ponto(UriComponentsBuilder uriBuilder) {
        var ponto = pontoService.ponto();
        var uri = uriBuilder.path("/ponto/{id}").buildAndExpand(ponto.getId()).toUri();
        return ResponseEntity.created(uri).body(new PontoViewModel(ponto));
    }

    @PostMapping("/justifica")
    @Operation(summary = "Marca ponto fora de horario", tags = {"ponto"})
    public ResponseEntity<PontoViewModel> justifica(@RequestBody @Valid JustificaPontoDTO dto, UriComponentsBuilder uriBuilder) {
        var ponto = pontoService.justifica(dto);
        var uri = uriBuilder.path("/ponto/{id}").buildAndExpand(ponto.getId()).toUri();
        return ResponseEntity.created(uri).body(new PontoViewModel(ponto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca ponto por ID", tags = {"ponto"})
    public ResponseEntity<PontoViewModel> pontos(@PathVariable Long id) {
        return ResponseEntity.ok(new PontoViewModel(pontoService.buscaPorId(id)));
    }

    @GetMapping
    @Operation(summary = "Busca ponto por período", tags = {"ponto"})
    public ResponseEntity<List<PontosViewModel>> pontos(@RequestParam("dataInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
                                                        @RequestParam("dataFim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {
        var pontos = pontoService.pontos(dataInicio, dataFim);
        return ResponseEntity.ok(PontosViewModel.parse(pontos));
    }

    @GetMapping("/download")
    @Operation(summary = "Download csv ponto por período", tags = {"ponto"})
    public void download(@RequestParam("dataInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
                                                        @RequestParam("dataFim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim,
                                                        HttpServletResponse response) throws IOException {
        response.addHeader("Content-Type", "text/csv");
        response.addHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + UUID.randomUUID() + ".csv");
        pontoService.download(dataInicio, dataFim, response.getWriter());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Ajusta ponto", tags = {"ponto"})
    public ResponseEntity<PontoViewModel> alteraPonto(@PathVariable Long id, @RequestBody @Valid AlteraPontoDTO dto) {
        var ponto = pontoService.alteraPonto(id, dto);
        return ResponseEntity.ok(new PontoViewModel(ponto));
    }
}
