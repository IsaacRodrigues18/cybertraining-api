CREATE TABLE tb_alternativas
(
    id_alternativa BIGSERIAL PRIMARY KEY,
    id_cenario     BIGINT                   NOT NULL,
    texto          TEXT                     NOT NULL,
    correta        BOOLEAN                  NOT NULL DEFAULT FALSE,
    feedback       TEXT                     NOT NULL,
    criado_em      TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    atualizado_em  TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,

    -- Chave estrangeira ligando a alternativa ao seu cenário
    CONSTRAINT fk_alternativas_cenarios
        FOREIGN KEY (id_cenario)
            REFERENCES tb_cenarios (id_cenario)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);

-- Índice obrigatório para consultas rápidas das opções de um cenário
    CREATE INDEX idx_alternativas_id_cenario
    ON tb_alternativas (id_cenario);