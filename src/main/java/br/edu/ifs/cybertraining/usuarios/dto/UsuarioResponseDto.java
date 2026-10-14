package br.edu.ifs.cybertraining.usuarios.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponseDto {

    private Long idUsuario;
    private String nomeUsuario;
    private String email;
    private Integer nivel;
    private Integer pontuacaoTotal;
    private OffsetDateTime criadoEm;
    private OffsetDateTime atualizadoEm;
}