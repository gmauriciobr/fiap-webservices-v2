package br.com.fiap.dto;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AtualizaUsuarioDTO {

    @NotEmpty
    private String nome;
}
