package br.com.fiap.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErroFormDTO {

    private String campo;
    private String erro;
}
