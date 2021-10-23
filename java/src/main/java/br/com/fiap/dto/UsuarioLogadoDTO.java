package br.com.fiap.dto;

import br.com.fiap.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsuarioLogadoDTO {

    private Long id;
    private String nome;
    private String email;

    public UsuarioLogadoDTO(Usuario usuario) {
        BeanUtils.copyProperties(usuario, this);
    }
}
