package br.edu.ifs.cybertraining.categorias;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long> {

    boolean existsByNomeIgnoreCase(String nome);

    boolean existsByNomeIgnoreCaseAndIdCategoriaNot(String nome, Long idCategoria);

    // Ajuste a consulta conforme o nome da entidade de Cenário/Desafio
    @Query("SELECT COUNT(c) > 0 FROM CenarioEntity c WHERE c.categoria.idCategoria = :id")
    boolean temCenariosVinculados(Long id);
}