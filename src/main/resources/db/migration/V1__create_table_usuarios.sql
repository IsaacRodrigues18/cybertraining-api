CREATE TABLE tb_usuarios
(
    id_usuario    BIGSERIAL PRIMARY KEY,
    nome_usuario  VARCHAR(100)             NOT NULL,
    email         VARCHAR(255)             NOT NULL UNIQUE,
    senha         VARCHAR(255)             NOT NULL,
    criado_em     TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    atualizado_em TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP
);