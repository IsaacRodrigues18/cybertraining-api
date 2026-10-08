package br.edu.ifs.cybertraining.cenarios;

import br.edu.ifs.cybertraining.categorias.CategoriaEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.OffsetDateTime;

@Entity
@Table(name = "tb_cenarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CenarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id_cenario", updatable = false)
    private Long idCenario;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_categoria", nullable = false)
    @ToString.Exclude
    private CategoriaEntity categoria;


    @Column(name = "titulo", nullable = false, unique = true, length = 150)
    private String titulo;

    @Column(name = "descricao", columnDefinition = "TEXT")
    private String descricao;


    @Column(name = "dificuldade", nullable = false)
    private Short dificuldade;


    @Column(name = "explicacao", nullable = false, columnDefinition = "TEXT")
    private String explicacao;


    @Column(name = "cenario_ativo", nullable = false)
    private Boolean cenarioAtivo = true;

    @Column(name = "criado_em", nullable = false, updatable = false)
    private OffsetDateTime criadoEm;

    @Column(name = "atualizado_em", nullable = false)
    private OffsetDateTime atualizadoEm;

    @PrePersist
    protected void onCreate() {
        OffsetDateTime agora = OffsetDateTime.now();
        this.criadoEm = agora;
        this.atualizadoEm = agora;
        if (this.cenarioAtivo == null) {
            this.cenarioAtivo = true;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.atualizadoEm = OffsetDateTime.now();
    }
}