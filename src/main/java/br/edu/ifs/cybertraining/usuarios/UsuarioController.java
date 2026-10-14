package br.edu.ifs.cybertraining.usuarios;

import br.edu.ifs.cybertraining.usuarios.dto.UsuarioRequestDto;
import br.edu.ifs.cybertraining.usuarios.dto.UsuarioResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDto> criarUsuario(
            @RequestBody @Valid UsuarioRequestDto requestDto,
            UriComponentsBuilder uriBuilder) {

        UsuarioResponseDto response = usuarioService.criarUsuario(requestDto);
        URI uri = uriBuilder.path("/api/v1/usuarios/{id}").buildAndExpand(response.getIdUsuario()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> listarTodosUsuarios() {
        return ResponseEntity.ok(usuarioService.listarTodosUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> buscarUsuarioPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> editarDadosUsuarios(
            @PathVariable Long id,
            @RequestBody @Valid UsuarioRequestDto requestDto) {

        return ResponseEntity.ok(usuarioService.editarDadosUsuarios(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        usuarioService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}