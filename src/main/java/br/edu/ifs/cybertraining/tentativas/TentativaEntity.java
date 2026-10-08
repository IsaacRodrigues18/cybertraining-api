package br.edu.ifs.cybertraining.tentativas;

import br.edu.ifs.cybertraining.alternativa.AlternativaEntity;
import br.edu.ifs.cybertraining.cenarios.CenarioEntity;
import br.edu.ifs.cybertraining.usuarios.UsuarioEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
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
@Table(name = "tb_tentativas")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TentativaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Setter(AccessLevel.NONE)
    @Column(name = "id_tentativa", updatable = false)
    private Long idTentativa;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario", nullable = false, updatable = false)
    @ToString.Exclude
    private UsuarioEntity usuario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_cenario", nullable = false, updatable = false)
    @ToString.Exclude
    private CenarioEntity cenario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_alternativa", nullable = false, updatable = false)
    @ToString.Exclude
    private AlternativaEntity alternativa;

    @Builder.Default
    @Column(name = "acertou", nullable = false, updatable = false)
    private Boolean acertou = false;

    @Builder.Default
    @Column(name = "pontos_obtidos", nullable = false, updatable = false)
    private Short pontosObtidos = 0;

    @Setter(AccessLevel.NONE)
    @Column(name = "respondido_em", nullable = false, updatable = false)
    private OffsetDateTime respondidoEm;

    @PrePersist
    protected void onCreate() {
        this.respondidoEm = OffsetDateTime.now();
        if (this.acertou == null) {
            this.acertou = false;
        }
        if (this.pontosObtidos == null) {
            this.pontosObtidos = 0;
        }
    }
}