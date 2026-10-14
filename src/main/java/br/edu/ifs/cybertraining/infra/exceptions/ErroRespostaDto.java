package br.edu.ifs.cybertraining.infra.exceptions;

import java.time.OffsetDateTime;


import java.time.OffsetDateTime;
import java.util.List;

public record ErroRespostaDto(
        int status,
        String erro,
        String mensagem,
        String caminho,
        OffsetDateTime momento,
        List<CampoErro> campos
) {
    public record CampoErro(String campo, String mensagem) {}

    // Construtor utilitário para erros simples (sem lista de campos)
    public ErroRespostaDto(int status, String erro, String mensagem, String caminho) {
        this(status, erro, mensagem, caminho, OffsetDateTime.now(), null);
    }
}