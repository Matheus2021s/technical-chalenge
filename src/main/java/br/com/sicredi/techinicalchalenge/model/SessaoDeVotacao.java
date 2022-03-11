package br.com.sicredi.techinicalchalenge.model;

import br.com.sicredi.techinicalchalenge.model.enums.StatusSessao;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class SessaoDeVotacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    private Pauta pauta;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusSessao status;

    @NotNull
    private LocalDateTime horarioInicial;

    @NotNull
    private LocalDateTime horarioFinal;

    @Builder.Default
    @OneToMany( fetch = FetchType.LAZY, mappedBy = "sessaoDeVotacao", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Voto> votos = new LinkedHashSet<>();

    @Builder.Default
    private Integer quantidadeVotosSim = 0;

    @Builder.Default
    private Integer quantidadeVotosNao = 0;


}
