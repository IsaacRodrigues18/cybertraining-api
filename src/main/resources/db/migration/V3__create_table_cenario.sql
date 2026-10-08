CREATE TABLE tb_cenarios
(
    id_cenario     BIGSERIAL PRIMARY KEY,
    id_categoria   BIGINT                   NOT NULL,
    titulo         VARCHAR(150)             NOT NULL UNIQUE,
    descricao      TEXT,
    dificuldade    SMALLINT                 NOT NULL,
    explicacao     TEXT                     NOT NULL,
    cenario_ativo  BOOLEAN                  NOT NULL DEFAULT TRUE,
    criado_em      TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    atualizado_em  TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,

    -- Validação da escala de dificuldade (ex: 1 a 5)
    CONSTRAINT ck_cenarios_dificuldade
        CHECK (dificuldade BETWEEN 1 AND 5),

    -- Chave estrangeira (ajustada para tb_categorias)
    CONSTRAINT fk_cenarios_categorias
        FOREIGN KEY (id_categoria)
            REFERENCES tb_categorias (id_categoria)
            ON DELETE RESTRICT
            ON UPDATE CASCADE
);

-- Índice obrigatório de produção para a Foreign Key
CREATE INDEX idx_cenarios_id_categoria
    ON tb_cenarios (id_categoria);