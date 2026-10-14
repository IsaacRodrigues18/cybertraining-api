package br.edu.ifs.cybertraining.infra.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;
import java.util.List;


@RestControllerAdvice
public class TratadorDeErrosGlobal {

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ErroRespostaDto> tratarNaoEncontrado(
            RecursoNaoEncontradoException ex, HttpServletRequest request) {

        ErroRespostaDto erro = new ErroRespostaDto(
                HttpStatus.NOT_FOUND.value(),
                "Recurso Não Encontrado",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    // 2. Captura o 400 da sua regra de negócio (ex: nome duplicado)
    @ExceptionHandler(RegraDeNegocioException.class)
    public ResponseEntity<ErroRespostaDto> tratarRegraDeNegocio(
            RegraDeNegocioException ex, HttpServletRequest request) {

        ErroRespostaDto erro = new ErroRespostaDto(
                HttpStatus.BAD_REQUEST.value(),
                "Regra de Negócio Violada",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroRespostaDto> tratarValidacaoCampos(
            MethodArgumentNotValidException ex, HttpServletRequest request) {

        List<ErroRespostaDto.CampoErro> campos = ex.getBindingResult().getFieldErrors().stream()
                .map(f -> new ErroRespostaDto.CampoErro(f.getField(), f.getDefaultMessage()))
                .toList();

        ErroRespostaDto erro = new ErroRespostaDto(
                HttpStatus.BAD_REQUEST.value(),
                "Dados Inválidos",
                "Um ou mais campos estão inválidos",
                request.getRequestURI(),
                OffsetDateTime.now(),
                campos
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }
}
