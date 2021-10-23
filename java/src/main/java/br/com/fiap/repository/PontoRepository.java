package br.com.fiap.repository;

import br.com.fiap.model.Ponto;
import java.time.Instant;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface PontoRepository extends CrudRepository<Ponto, Long> {

    List<Ponto> findByUsuarioIdAndMarcacaoBetween(Long id, Instant dataInicio, Instant dataFim);

}