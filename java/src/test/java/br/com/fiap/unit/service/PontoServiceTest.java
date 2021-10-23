package br.com.fiap.unit.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.fiap.dto.UsuarioLogadoDTO;
import br.com.fiap.model.Ponto;
import br.com.fiap.model.Usuario;
import br.com.fiap.repository.PontoRepository;
import br.com.fiap.service.PontoService;
import io.github.glytching.junit.extension.random.Random;
import io.github.glytching.junit.extension.random.RandomBeansExtension;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.server.ResponseStatusException;

@ExtendWith({MockitoExtension.class, RandomBeansExtension.class})
public class PontoServiceTest {

    @InjectMocks
    PontoService pontoService;

    @Mock
    PontoRepository pontoRepository;

    @Mock
    EntityManager em;

    @Random
    Long idPonto;

    @Random
    Ponto ponto;

    @Random(type = Ponto.class, size = 3)
    List<Ponto> pontoList;

    @Random
    LocalDate dataInicio;

    @Random
    LocalDate dataFim;

    @Random
    Usuario usuario;

    @Random
    Long idUsuario;

    @BeforeEach
    void beforeEach() {
        var authentication = new UsernamePasswordAuthenticationToken(new UsuarioLogadoDTO(usuario), null, usuario.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Test
    void quandoPontoValido_QuandoBuscar_Sucesso() {
        when(pontoRepository.findById(anyLong())).thenReturn(Optional.of(ponto));
        Assertions.assertDoesNotThrow(() -> {
            pontoService.buscaPorId(idPonto);
            verify(pontoRepository, times(1)).findById(anyLong());
        });
    }

    @Test
    void quandoPontoInvalido_QuandoBuscar_Erro() {
        when(pontoRepository.findById(anyLong())).thenReturn(Optional.empty());
        Assertions.assertThrows(ResponseStatusException.class, () -> {
            pontoService.buscaPorId(idPonto);
        });
    }

    @Test
    void quandoPontoInvalido_QuandoSalvar_Sucesso() {
        when(pontoRepository.save(any(Ponto.class))).thenReturn(ponto);
        Assertions.assertDoesNotThrow(() -> {
            pontoService.ponto();
            verify(pontoRepository, times(1)).save(any(Ponto.class));
        });
    }

    @Test
    void quandoFiltroValido_QuandoBuscaPonto_Sucesso() {
        when(pontoRepository.findByUsuarioIdAndMarcacaoBetween(anyLong(), any(Instant.class), any(Instant.class))).thenReturn(pontoList);
        Assertions.assertDoesNotThrow(() -> {
            pontoService.pontos(dataInicio, dataFim);
            verify(pontoRepository, times(1))
                .findByUsuarioIdAndMarcacaoBetween(anyLong(), any(Instant.class), any(Instant.class));
        });
    }
}
