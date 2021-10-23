package br.com.fiap.view;

import br.com.fiap.model.Usuario;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class UsuarioViewModel {

    public Long id;
    public String nome;
    private String email;

    public UsuarioViewModel(Usuario usuario) {
        BeanUtils.copyProperties(usuario, this);
    }
}
