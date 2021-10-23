package br.com.fiap.unit.service;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.fiap.model.Usuario;
import br.com.fiap.repository.UsuarioRepository;
import br.com.fiap.service.AutenticacaoService;
import io.github.glytching.junit.extension.random.Random;
import io.github.glytching.junit.extension.random.RandomBeansExtension;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@ExtendWith({MockitoExtension.class, RandomBeansExtension.class})
public class AutenticacaoServiceTest {

    @InjectMocks
    AutenticacaoService autenticacaoService;

    @Mock
    UsuarioRepository usuarioRepository;

    @Random
    String login;

    @Random
    Usuario usuario;

    @Test
    public void quandoUsuarioValido_QuandoLogar_Sucesso() {
        when(usuarioRepository.findByEmail(anyString())).thenReturn(Optional.of(usuario));
        autenticacaoService.loadUserByUsername(login);
        verify(usuarioRepository, times(1)).findByEmail(anyString());
    }

    @Test
    public void quandoUsuarioInvalido_QuandoLogar_Erro() {
        when(usuarioRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        Assertions.assertThrows(UsernameNotFoundException.class, () -> {
            autenticacaoService.loadUserByUsername(login);
        });
    }

}
