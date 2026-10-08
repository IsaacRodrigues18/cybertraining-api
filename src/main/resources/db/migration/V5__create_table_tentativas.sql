CREATE TABLE tb_tentativas (
                               id_tentativa    BIGSERIAL PRIMARY KEY,
                               id_usuario      BIGINT                   NOT NULL,
                               id_cenario      BIGINT                   NOT NULL,
                               id_alternativa  BIGINT                   NOT NULL,
                               acertou         BOOLEAN                  NOT NULL DEFAULT FALSE,
                               pontos_obtidos  SMALLINT                 NOT NULL DEFAULT 0,
                               respondido_em   TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,


                               CONSTRAINT fk_tentativas_usuarios
                                   FOREIGN KEY (id_usuario)
                                       REFERENCES tb_usuarios (id_usuario)
                                       ON DELETE RESTRICT
                                       ON UPDATE CASCADE,


                               CONSTRAINT fk_tentativas_cenarios
                                   FOREIGN KEY (id_cenario)
                                       REFERENCES tb_cenarios (id_cenario)
                                       ON DELETE RESTRICT
                                       ON UPDATE CASCADE,


                               CONSTRAINT fk_tentativas_alternativas
                                   FOREIGN KEY (id_alternativa)
                                       REFERENCES tb_alternativas (id_alternativa)
                                       ON DELETE RESTRICT
                                       ON UPDATE CASCADE
);

CREATE INDEX idx_tentativas_id_usuario ON tb_tentativas (id_usuario);
CREATE INDEX idx_tentativas_id_cenario ON tb_tentativas (id_cenario);