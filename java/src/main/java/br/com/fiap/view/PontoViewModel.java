package br.com.fiap.view;

import br.com.fiap.model.Ponto;
import java.time.Instant;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class PontoViewModel {

    private Long id;
    private Instant marcacao;
    private String observacao;
    private Instant dataCriacao;
    private Instant dataAlteracao;

    public PontoViewModel(Ponto ponto) {
        BeanUtils.copyProperties(ponto, this);
    }
}
