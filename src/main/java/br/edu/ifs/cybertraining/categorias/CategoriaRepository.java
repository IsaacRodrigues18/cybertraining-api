package br.edu.ifs.cybertraining.categorias;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Long, CategoriaEntity> {

    List<CategoriaEntity> findByNome();
    boolean existByNome(String nome);

}
