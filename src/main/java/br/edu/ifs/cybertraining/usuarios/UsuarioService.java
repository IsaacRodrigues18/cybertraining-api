package br.edu.ifs.cybertraining.usuarios;

import br.edu.ifs.cybertraining.infra.exceptions.RecursoNaoEncontradoException;
import br.edu.ifs.cybertraining.infra.exceptions.RegraDeNegocioException;
import br.edu.ifs.cybertraining.usuarios.dto.UsuarioRequestDto;
import br.edu.ifs.cybertraining.usuarios.dto.UsuarioResponseDto;
import br.edu.ifs.cybertraining.usuarios.dto.mapper.UsuarioMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository,
                          UsuarioMapper usuarioMapper,
                          PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UsuarioResponseDto criarUsuario(UsuarioRequestDto requestDto) {
        if (usuarioRepository.existsByEmailIgnoreCase(requestDto.getEmail())) {
            throw new RegraDeNegocioException("O email " + requestDto.getEmail() + " já está cadastrado.");
        }

        UsuarioEntity entity = usuarioMapper.toEntity(requestDto);
        // Criptografa a senha antes de persistir no banco
        entity.setSenha(passwordEncoder.encode(requestDto.getSenha()));

        UsuarioEntity salvo = usuarioRepository.save(entity);
        return usuarioMapper.toResponseDTO(salvo);
    }

    @Transactional(readOnly = true)
    public List<UsuarioResponseDto> listarTodosUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(usuarioMapper::toResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public UsuarioResponseDto buscarUsuarioPorId(Long idUsuario) {
        UsuarioEntity usuarioEntity = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado com o ID: " + idUsuario));

        return usuarioMapper.toResponseDTO(usuarioEntity);
    }

    @Transactional
    public UsuarioResponseDto editarDadosUsuarios(Long idUsuario, UsuarioRequestDto requestDto) {
        UsuarioEntity entity = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado com o ID: " + idUsuario));

        if (usuarioRepository.existsByEmailIgnoreCaseAndIdUsuarioNot(requestDto.getEmail(), idUsuario)) {
            throw new RegraDeNegocioException("O email " + requestDto.getEmail() + " já pertence a outro usuário.");
        }

        entity.setNomeUsuario(requestDto.getNomeUsuario());
        entity.setEmail(requestDto.getEmail());
        // Se a senha for enviada na edição, aplica o hash novamente
        if (requestDto.getSenha() != null && !requestDto.getSenha().isBlank()) {
            entity.setSenha(passwordEncoder.encode(requestDto.getSenha()));
        }

        UsuarioEntity atualizado = usuarioRepository.save(entity);
        return usuarioMapper.toResponseDTO(atualizado);
    }

    @Transactional
    public void deletarUsuario(Long idUsuario) {
        if (!usuarioRepository.existsById(idUsuario)) {
            throw new RecursoNaoEncontradoException("Usuário não encontrado com o ID: " + idUsuario);
        }
        usuarioRepository.deleteById(idUsuario);
    }
}