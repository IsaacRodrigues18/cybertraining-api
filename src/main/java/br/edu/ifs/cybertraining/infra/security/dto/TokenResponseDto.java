package br.edu.ifs.cybertraining.infra.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TokenResponseDto {

    private String token;
    private String tipo; // Geralmente "Bearer"
}