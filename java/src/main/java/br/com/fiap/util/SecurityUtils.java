package br.com.fiap.util;

import br.com.fiap.dto.UsuarioLogadoDTO;
import br.com.fiap.exception.ApiException;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    public static UsuarioLogadoDTO getUsuarioLogado() {
        var ctx = SecurityContextHolder.getContext();

        if (ctx.getAuthentication() == null) {
            throw new ApiException("Authentication not found");
        }
        if (!(ctx.getAuthentication().getPrincipal() instanceof UsuarioLogadoDTO)) {
            throw new ApiException("User class cast exception");
        }
        return (UsuarioLogadoDTO) ctx.getAuthentication().getPrincipal();
    }
}
