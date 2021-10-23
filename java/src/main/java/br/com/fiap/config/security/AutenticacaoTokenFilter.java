package br.com.fiap.config.security;

import br.com.fiap.dto.UsuarioLogadoDTO;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
public class AutenticacaoTokenFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        var token = recuerarToken(request);
        boolean valido = tokenService.isTokenValido(token);
        if(valido) this.autenticarClientes(token);
        filterChain.doFilter(request, response);
    }

    private String recuerarToken(HttpServletRequest request) {
        var token = request.getHeader("Authorization");
        if(token != null && token.trim() != "" && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return null;
    }

    private void autenticarClientes(String token) {
        var usuario = this.tokenService.getUsuarioByToken(token);
        var authentication = new UsernamePasswordAuthenticationToken(new UsuarioLogadoDTO(usuario), null, usuario.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
