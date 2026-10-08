package br.edu.ifs.cybertraining.alternativa;


import br.edu.ifs.cybertraining.cenarios.CenarioEntity;
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
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.OffsetDateTime;

@Entity
@Table(name = "tb_alternativas")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AlternativaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Setter(AccessLevel.NONE)
    @Column(name = "id_alternativa", updatable = false)
    private Long idAlternativa;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_cenario", nullable = false)
    @ToString.Exclude
    private CenarioEntity cenario;

    @Column(name = "texto", nullable = false, columnDefinition = "TEXT")
    private String texto;

    @Builder.Default
    @Column(name = "correta", nullable = false)
    private Boolean correta = false;

    @Column(name = "feedback", nullable = false, columnDefinition = "TEXT")
    private String feedback;

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
        if (this.correta == null) {
            this.correta = false;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.atualizadoEm = OffsetDateTime.now();
    }
}