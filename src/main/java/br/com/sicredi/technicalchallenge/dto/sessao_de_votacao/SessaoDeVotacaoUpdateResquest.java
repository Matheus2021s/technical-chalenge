package br.com.sicredi.technicalchallenge.dto.sessao_de_votacao;


import br.com.sicredi.technicalchallenge.dto.voto.VotoUpdateResquest;
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
public class SessaoDeVotacaoUpdateResquest {
    private Long id;
    private StatusSessao status;
    private LocalDateTime horarioInicial;
    private LocalDateTime horarioFinal;
    @Builder.Default
    private Set<VotoUpdateResquest> votos = new LinkedHashSet<>();

    public SessaoDeVotacaoUpdateResquest(SessaoDeVotacao sessaoDeVotacao) {
        this.id = sessaoDeVotacao.getId();
    }

    public static List<SessaoDeVotacaoResponse> toListRequest(List<SessaoDeVotacao> listSessaoDeVotacaos) {
        return listSessaoDeVotacaos.stream().map(SessaoDeVotacaoResponse::new).collect(Collectors.toList());
    }

    public static SessaoDeVotacaoUpdateResquest convetWithoutPauta(SessaoDeVotacao sessaoDeVotacao) {
        return SessaoDeVotacaoUpdateResquest.builder()
                .id(sessaoDeVotacao.getId())
                .status(sessaoDeVotacao.getStatus())
                .horarioInicial(sessaoDeVotacao.getHorarioInicial())
                .horarioFinal(sessaoDeVotacao.getHorarioFinal())
                .build();
    }

    public SessaoDeVotacao convert() {
        return SessaoDeVotacao.builder()
                .id(getId())
                .status(getStatus())
                .horarioInicial(getHorarioInicial())
                .horarioFinal(getHorarioFinal())
                .votos(composeVotos(getVotos()))
                .build();
    }
    private static Set<Voto> composeVotos(Set<VotoUpdateResquest> votos) {
        return votos.stream().map(VotoUpdateResquest::convert).collect(Collectors.toSet());
    }

    public SessaoDeVotacao convertWithoutVotos() {
        return SessaoDeVotacao.builder()
                .id(getId())
                .status(getStatus())
                .horarioInicial(getHorarioInicial())
                .horarioFinal(getHorarioFinal())
                .build();
    }
}

