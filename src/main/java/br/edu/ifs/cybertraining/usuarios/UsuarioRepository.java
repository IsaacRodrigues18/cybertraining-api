package br.edu.ifs.cybertraining.usuarios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    List<UsuarioEntity> findAll();

    boolean existsByEmailIgnoreCase(String email);

    Optional<UsuarioEntity> findByEmailIgnoreCase(String email);

    boolean existsByEmailIgnoreCaseAndIdUsuarioNot(String email, Long idUsuario);
}