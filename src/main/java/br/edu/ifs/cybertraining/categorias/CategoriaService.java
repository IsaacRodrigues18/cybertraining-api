package br.edu.ifs.cybertraining.categorias;

import br.edu.ifs.cybertraining.categorias.dto.CategoriaRequestDto;
import br.edu.ifs.cybertraining.categorias.dto.CategoriaResponseDto;
import br.edu.ifs.cybertraining.categorias.dto.mapper.CategoriaMapper;

import br.edu.ifs.cybertraining.infra.exceptions.RecursoNaoEncontradoException;
import br.edu.ifs.cybertraining.infra.exceptions.RegraDeNegocioException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository repository;
    private final CategoriaMapper mapper;

    @Transactional
    public CategoriaResponseDto criar(CategoriaRequestDto dto) {
        if (repository.existsByNomeIgnoreCase(dto.getNome())) {
            throw new RegraDeNegocioException("Já existe uma categoria cadastrada com o nome: " + dto.getNome());
        }

        CategoriaEntity entity = mapper.toEntity(dto);
        CategoriaEntity salva = repository.save(entity);
        return mapper.toResponse(salva);
    }

    @Transactional(readOnly = true)
    public List<CategoriaResponseDto> listarTodas() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public CategoriaResponseDto buscarPorId(Long id) {
        CategoriaEntity entity = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Categoria não encontrada com o ID: " + id));
        return mapper.toResponse(entity);
    }

    @Transactional
    public CategoriaResponseDto atualizar(Long id, CategoriaRequestDto dto) {
        CategoriaEntity entity = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Categoria não encontrada com o ID: " + id));


        if (repository.existsByNomeIgnoreCaseAndIdCategoriaNot(dto.getNome(), id)) {
            throw new RegraDeNegocioException("Já existe outra categoria cadastrada com o nome: " + dto.getNome());
        }

        entity.setNome(dto.getNome());
        entity.setDescricao(dto.getDescricao());

        CategoriaEntity atualizada = repository.save(entity);
        return mapper.toResponse(atualizada);
    }

    @Transactional
    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Categoria não encontrada com o ID: " + id);
        }

        if (repository.temCenariosVinculados(id)) {
            throw new RegraDeNegocioException("Não é possível excluir uma categoria que possui cenários/desafios vinculados.");
        }

        repository.deleteById(id);
    }
}