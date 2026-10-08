package br.edu.ifs.cybertraining.categorias.dto.mapper;

import br.edu.ifs.cybertraining.categorias.CategoriaEntity;
import br.edu.ifs.cybertraining.categorias.dto.CategoriaRequestDto;
import br.edu.ifs.cybertraining.categorias.dto.CategoriaResponseDto;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {

    public CategoriaEntity toEntity(CategoriaRequestDto dto) {
        if (dto == null) return null;

        CategoriaEntity categoria = new CategoriaEntity();
        categoria.setNome(dto.getNome());
        categoria.setDescricao(dto.getDescricao());
        return categoria;
    }

    public CategoriaResponseDto toResponse(CategoriaEntity entity) {
        if (entity == null) return null;

        CategoriaResponseDto dto = new CategoriaResponseDto();
        dto.setIdCategoria(entity.getIdCategoria());
        dto.setNome(entity.getNome());
        dto.setDescricao(entity.getDescricao());
        dto.setCriadoEm(entity.getCriadoEm());
        dto.setAtualizadoEm(entity.getAtualizadoEm());
        return dto;
    }
}