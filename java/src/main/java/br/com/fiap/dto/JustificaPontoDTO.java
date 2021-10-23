package br.com.fiap.dto;

import br.com.fiap.model.Ponto;
import java.time.Instant;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class JustificaPontoDTO {

    @NotNull
    private Instant marcacao;
    @NotEmpty
    private String justificativa;

    public static Ponto parse(JustificaPontoDTO dto) {
        var ponto = new Ponto();
        BeanUtils.copyProperties(dto, ponto);
        return ponto;
    }
}
