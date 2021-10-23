package br.com.fiap.config.security;

import br.com.fiap.model.Usuario;
import br.com.fiap.repository.UsuarioRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.LocalDate;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final UsuarioRepository usuarioRepository;

    @Value("${jwt.chave:abcd}")
    private String chave;

    @Value("${jwt.expire:1}")
    private Long expire;

    public String gerarToken(Authentication authentication) {
        var usuario = (Usuario) authentication.getPrincipal();
        Date expiracao = java.sql.Date.valueOf(LocalDate.now().plusDays(expire));

        return Jwts.builder()
            .setIssuer("API")
            .setSubject(usuario.getId().toString())
            .setIssuedAt(new Date())
            .setExpiration(expiracao)
            .signWith(SignatureAlgorithm.HS256, chave)
            .compact();
    }

    public boolean isTokenValido(String token) {
        try {
            Jwts.parser().setSigningKey(chave).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Usuario getUsuarioByToken(String token) {
        Claims body = Jwts.parser().setSigningKey(chave).parseClaimsJws(token).getBody();
        Usuario usuario = this.usuarioRepository.findById(Long.parseLong(body.getSubject())).get();
        return usuario;
    }
}
