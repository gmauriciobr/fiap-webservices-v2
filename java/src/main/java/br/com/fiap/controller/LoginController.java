package br.com.fiap.controller;

import br.com.fiap.dto.LoginDTO;
import br.com.fiap.config.security.TokenService;
import br.com.fiap.view.TokenViewModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
@Tag(name = "login", description = "Serviço para login")
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping
    @Operation(summary = "Login do usuário", tags = {"login"})
    public ResponseEntity<TokenViewModel> login(@RequestBody @Valid LoginDTO dto) {
        return ResponseEntity.ok(
            new TokenViewModel(tokenService.gerarToken(authenticationManager.authenticate(dto.parse()))));
    }

}
