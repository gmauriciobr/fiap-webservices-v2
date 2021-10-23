package br.com.fiap.view;

import lombok.Data;

@Data
public class TokenViewModel {

    private String token;
    private String tipo = "Bearer";

    public TokenViewModel(String token) {
        this.token = token;
    }
}
