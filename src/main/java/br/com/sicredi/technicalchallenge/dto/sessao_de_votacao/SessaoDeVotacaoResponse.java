package br.com.sicredi.technicalchallenge.dto.sessao_de_votacao;

import br.com.sicredi.technicalchallenge.dto.voto.VotoResponse;
import br.com.sicredi.technicalchallenge.model.SessaoDeVotacao;
import br.com.sicredi.technicalchallenge.model.Voto;
import br.com.sicredi.technicalchallenge.model.enums.StatusSessao;
import lombok.*;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SessaoDeVotacaoResponse {
    private Long id;
    private StatusSessao status;
    private LocalDateTime horarioInicial;
    private LocalDateTime horarioFinal;
    private Integer quantidadeVotosSim;
    private Integer quantidadeVotosNao;

    @Builder.Default
    private Set<VotoResponse> votos = new LinkedHashSet<>();

    public SessaoDeVotacaoResponse(SessaoDeVotacao sessaoDeVotacao) {
        this.id = sessaoDeVotacao.getId();
        this.status = sessaoDeVotacao.getStatus();
        this.horarioInicial = sessaoDeVotacao.getHorarioInicial();
        this.horarioFinal = sessaoDeVotacao.getHorarioFinal();
        this.votos = composeVotos(sessaoDeVotacao.getVotos());
        this.quantidadeVotosSim = sessaoDeVotacao.getQuantidadeVotosSim();
        this.quantidadeVotosNao = sessaoDeVotacao.getQuantidadeVotosNao();
    }

    public static SessaoDeVotacaoResponse convertWithoutPauta(SessaoDeVotacao sessaoDeVotacao) {
            return SessaoDeVotacaoResponse.builder()
                    .id(sessaoDeVotacao.getId())
                    .status(sessaoDeVotacao.getStatus())
                    .horarioInicial(sessaoDeVotacao.getHorarioInicial())
                    .horarioFinal(sessaoDeVotacao.getHorarioFinal())
                    .quantidadeVotosNao(sessaoDeVotacao.getQuantidadeVotosNao())
                    .quantidadeVotosSim(sessaoDeVotacao.getQuantidadeVotosSim())
                    .build();
    }

    private Set<VotoResponse> composeVotos(Set<Voto> votos) {
        return votos.stream().map(VotoResponse::new).collect(Collectors.toSet());
    }

    public static List<SessaoDeVotacaoResponse> toListResponse(List<SessaoDeVotacao> listSessaoDeVotacaos) {
        return listSessaoDeVotacaos.stream().map(SessaoDeVotacaoResponse::new).collect(Collectors.toList());
    }

    public SessaoDeVotacao convertWithoutVotos() {
        return SessaoDeVotacao.builder()
                .id(getId())
                .status(getStatus())
                .horarioInicial(getHorarioInicial())
                .horarioFinal(getHorarioFinal())
                .quantidadeVotosNao(getQuantidadeVotosNao())
                .quantidadeVotosSim(getQuantidadeVotosSim())
                .build();
    }
}
