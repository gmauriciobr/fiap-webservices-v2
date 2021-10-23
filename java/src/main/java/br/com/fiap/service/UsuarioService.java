package br.com.fiap.service;

import br.com.fiap.dto.AtualizaUsuarioDTO;
import br.com.fiap.dto.UsuarioDTO;
import br.com.fiap.exception.ApiException;
import br.com.fiap.model.Usuario;
import br.com.fiap.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder enconder;

    public Usuario salvar(UsuarioDTO dto) {
        if(usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new ApiException("Email já cadastrado");
        }
        var usuario = UsuarioDTO.parse(dto);
        usuario.setPassword(enconder.encode(dto.getPassword()));
        return usuarioRepository.save(usuario);
    }

    public Usuario buscaPorId(Long id) {
        return usuarioRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ("Cadastro não encontrado")));
    }

    public Usuario alteraUsuario(Long id, AtualizaUsuarioDTO dto) {
        var usuario = buscaPorId(id);
        BeanUtils.copyProperties(dto, usuario);
        return usuarioRepository.save(usuario);
    }
}
