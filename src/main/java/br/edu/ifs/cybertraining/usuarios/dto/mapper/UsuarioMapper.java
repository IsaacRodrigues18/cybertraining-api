package br.edu.ifs.cybertraining.usuarios.dto.mapper;

import br.edu.ifs.cybertraining.usuarios.UsuarioEntity;
import br.edu.ifs.cybertraining.usuarios.dto.UsuarioRequestDto;
import br.edu.ifs.cybertraining.usuarios.dto.UsuarioResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public UsuarioEntity toEntity(UsuarioRequestDto dto) {
        UsuarioEntity usuarioEntity = new UsuarioEntity();

        usuarioEntity.setNomeUsuario(dto.getNomeUsuario());
        usuarioEntity.setEmail(dto.getEmail());
        usuarioEntity.setSenha(dto.getSenha());

        return usuarioEntity;
    }

    public UsuarioResponseDto toResponseDTO(UsuarioEntity entity) {
        UsuarioResponseDto dto = new UsuarioResponseDto();

        dto.setIdUsuario(entity.getIdUsuario());
        dto.setNomeUsuario(entity.getNomeUsuario());
        dto.setEmail(entity.getEmail());
        dto.setCriadoEm(entity.getCriadoEm());
        dto.setAtualizadoEm(entity.getAtualizadoEm());

        return dto;
    }
}