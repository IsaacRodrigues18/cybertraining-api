package br.edu.ifs.cybertraining.categorias.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaResponseDto {

    private Long idCategoria;
    private String nome;
    private String descricao;
    private OffsetDateTime criadoEm;
    private OffsetDateTime atualizadoEm;
}