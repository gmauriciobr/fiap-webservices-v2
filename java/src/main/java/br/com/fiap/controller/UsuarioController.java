package br.com.fiap.controller;

import br.com.fiap.dto.AtualizaUsuarioDTO;
import br.com.fiap.dto.UsuarioDTO;
import br.com.fiap.service.UsuarioService;
import br.com.fiap.view.UsuarioViewModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuario")
@Tag(name = "usuario", description = "Serviço para administração do usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    @Operation(summary = "Cadastro de usuário", tags = {"usuario"})
    public ResponseEntity<UsuarioViewModel> cadastro(@RequestBody @Valid UsuarioDTO dto, UriComponentsBuilder uriBuilder) {
        var usuario = usuarioService.salvar(dto);
        var uri = uriBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new UsuarioViewModel(usuario));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca usuario por ID", tags = {"usuario"})
    public ResponseEntity<UsuarioViewModel> buscaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(new UsuarioViewModel(usuarioService.buscaPorId(id)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Busca usuario por ID", tags = {"usuario"})
    public ResponseEntity<UsuarioViewModel> alteraUsuario(@PathVariable Long id, @RequestBody @Valid AtualizaUsuarioDTO dto) {
        return ResponseEntity.ok(new UsuarioViewModel(usuarioService.alteraUsuario(id, dto)));
    }

}
