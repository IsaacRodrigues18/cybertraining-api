package br.edu.ifs.cybertraining.categorias;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "tb_categorias")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CategoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Setter(AccessLevel.NONE)
    @Column(name = "id_categoria", updatable = false)
    private Long idCategoria;

    @Column(name = "nome", nullable = false, unique = true, length = 150)
    private String nome;

    @Column(name = "descricao", length = 255)
    private String descricao;

    @Setter(AccessLevel.NONE)
    @Column(name = "criado_em", nullable = false, updatable = false)
    private OffsetDateTime criadoEm;

    @Setter(AccessLevel.NONE)
    @Column(name = "atualizado_em", nullable = false)
    private OffsetDateTime atualizadoEm;

    @PrePersist
    protected void onCreate() {
        OffsetDateTime agora = OffsetDateTime.now();
        this.criadoEm = agora;
        this.atualizadoEm = agora;
    }

    @PreUpdate
    protected void onUpdate() {
        this.atualizadoEm = OffsetDateTime.now();
    }
}