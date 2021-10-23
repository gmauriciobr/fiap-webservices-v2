package br.com.fiap.dto;

import br.com.fiap.model.Usuario;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class UsuarioDTO {

    @NotEmpty
    private String nome;
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;

    public static Usuario parse(UsuarioDTO dto) {
        var usuario = new Usuario();
        BeanUtils.copyProperties(dto, usuario);
        return usuario;
    }
}
