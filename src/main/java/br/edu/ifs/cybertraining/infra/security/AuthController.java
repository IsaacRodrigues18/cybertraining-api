package br.edu.ifs.cybertraining.infra.security;

import br.edu.ifs.cybertraining.infra.security.dto.LoginRequestDto;
import br.edu.ifs.cybertraining.infra.security.dto.TokenResponseDto;
import br.edu.ifs.cybertraining.usuarios.UsuarioEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody @Valid LoginRequestDto dados) {
        var authToken = new UsernamePasswordAuthenticationToken(dados.getEmail(), dados.getSenha());
        Authentication auth = authenticationManager.authenticate(authToken);

        String tokenJWT = tokenService.gerarToken((UsuarioEntity) auth.getPrincipal());

        return ResponseEntity.ok(new TokenResponseDto(tokenJWT, "Bearer"));
    }
}