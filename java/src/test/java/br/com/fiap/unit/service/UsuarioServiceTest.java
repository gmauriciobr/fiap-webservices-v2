package br.com.fiap.unit.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.fiap.dto.AtualizaUsuarioDTO;
import br.com.fiap.dto.UsuarioDTO;
import br.com.fiap.exception.ApiException;
import br.com.fiap.model.Usuario;
import br.com.fiap.repository.UsuarioRepository;
import br.com.fiap.service.UsuarioService;
import io.github.glytching.junit.extension.random.Random;
import io.github.glytching.junit.extension.random.RandomBeansExtension;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

@ExtendWith({MockitoExtension.class, RandomBeansExtension.class})
public class UsuarioServiceTest {

    @InjectMocks
    UsuarioService usuarioService;

    @Mock
    UsuarioRepository usuarioRepository;

    @Mock
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Random
    UsuarioDTO usuarioDTO;

    @Random
    AtualizaUsuarioDTO atualizaUsuarioDTO;

    @Random
    Usuario usuario;

    @Random
    Long idUsuario;

    @Test
    void quandoUsuarioValido_QuandoSalvar_Sucesso() {
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);
        usuarioService.salvar(usuarioDTO);
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    void quandoUsuarioJaCadastrado_QuandoSalvar_Sucesso() {
        when(usuarioRepository.existsByEmail(anyString())).thenReturn(true);
        Assertions.assertThrows(ApiException.class, () -> {
            usuarioService.salvar(usuarioDTO);
            verify(usuarioRepository, times(0)).save(any(Usuario.class));
        });
    }

    @Test
    void quandoUsuarioValido_QuandoBuscaPorId_Sucesso() {
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuario));
        usuarioService.buscaPorId(idUsuario);
        verify(usuarioRepository, times(1)).findById(anyLong());
    }

    @Test
    void quandoUsuarioInvalido_QuandoBuscaPorId_Erro() {
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.empty());
        Assertions.assertThrows(ResponseStatusException.class, () -> {
            usuarioService.buscaPorId(idUsuario);
        });
    }

    @Test
    public void quandoUsuarioValido_QuandoalteraUsuario_Sucesso() {
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuario));
        Assertions.assertDoesNotThrow(() -> {
            usuarioService.alteraUsuario(idUsuario, atualizaUsuarioDTO);
            verify(usuarioRepository, times(1)).save(any(Usuario.class));
        });
    }
}
