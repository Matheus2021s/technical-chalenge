package br.com.sicredi.techinicalchalenge.dto.sessao_de_votacao;

import br.com.sicredi.techinicalchalenge.dto.pauta.PautaResquest;
import br.com.sicredi.techinicalchalenge.dto.voto.VotoResquest;
import br.com.sicredi.techinicalchalenge.model.SessaoDeVotacao;
import br.com.sicredi.techinicalchalenge.model.Voto;
import lombok.*;

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
    private PautaResquest pauta = new PautaResquest();
    @Builder.Default
    private Set<VotoResquest> votos = new LinkedHashSet<>();

    public SessaoDeVotacaoResquest(SessaoDeVotacao sessaoDeVotacao) {
        this.id = sessaoDeVotacao.getId();
        this.pauta = new PautaResquest(sessaoDeVotacao.getPauta());
        this.votos = composeVotosRequest(sessaoDeVotacao.getVotos());
    }

    private Set<VotoResquest> composeVotosRequest(Set<Voto> votos) {
        return votos.stream().map(VotoResquest::new).collect(Collectors.toSet());
    }

    public SessaoDeVotacao convert() {
        return SessaoDeVotacao.builder()
                .id(getId())
                .pauta(getPauta().convert())
                .votos(composeVotos(getVotos()))
                .build();
    }

    private Set<Voto> composeVotos(Set<VotoResquest> votos) {
        return votos.stream().map(VotoResquest::convert).collect(Collectors.toSet());
    }

    public SessaoDeVotacao convertWithoutVotos() {
        return SessaoDeVotacao.builder()
                .id(getId())
                .pauta(getPauta().convert())
                .build();
    }
}
