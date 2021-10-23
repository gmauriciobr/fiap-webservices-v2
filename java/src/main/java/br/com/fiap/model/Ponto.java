package br.com.fiap.model;

import java.time.Instant;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Data
@Entity
@Table(name = "ponto")
public class Ponto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;
    private Instant marcacao;
    private String justificativa;
    @CreatedDate
    private Instant dataCriacao;
    @LastModifiedDate
    private Instant dataAlteracao;

}
