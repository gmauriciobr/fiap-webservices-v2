package br.com.fiap.dto;

import java.time.Instant;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AlteraPontoDTO {

    @NotNull
    private Instant marcacao;
    @NotEmpty
    private String justificativa;
}
