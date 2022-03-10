package br.com.sicredi.techinicalchalenge.dto.sessao_de_votacao;

import br.com.sicredi.techinicalchalenge.dto.pauta.PautaResponse;
import br.com.sicredi.techinicalchalenge.dto.pauta.PautaResquest;
import br.com.sicredi.techinicalchalenge.dto.voto.VotoResponse;
import br.com.sicredi.techinicalchalenge.model.SessaoDeVotacao;
import br.com.sicredi.techinicalchalenge.model.Voto;
import br.com.sicredi.techinicalchalenge.model.enums.StatusSessao;
import lombok.*;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessaoDeVotacaoResquest {
    private Long id;
    private PautaResquest pauta;
    private StatusSessao status;
    private LocalDateTime horarioInicial;
    private LocalDateTime horarioFinal;
    @Builder.Default
    private Set<VotoResponse> votos = new LinkedHashSet<>();

    public SessaoDeVotacaoResquest(SessaoDeVotacao sessaoDeVotacao) {
        this.id = sessaoDeVotacao.getId();
    }

    public SessaoDeVotacao convert() {
        return SessaoDeVotacao.builder()
                .id(getId())
                .pauta(getPauta().convert())
                .status(getStatus())
                .horarioInicial(getHorarioInicial())
                .horarioFinal(getHorarioFinal())
                .votos(composeVotos(getVotos()))
                .build();
    }

    private Set<Voto> composeVotos(Set<VotoResponse> votos) {
        return votos.stream().map(VotoResponse::convert).collect(Collectors.toSet());
    }

}
