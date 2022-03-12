package br.com.sicredi.technicalchallenge.dto.sessao_de_votacao;

import br.com.sicredi.technicalchallenge.dto.pauta.PautaResquest;
import br.com.sicredi.technicalchallenge.dto.voto.VotoResquest;
import br.com.sicredi.technicalchallenge.model.SessaoDeVotacao;
import br.com.sicredi.technicalchallenge.model.Voto;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SessaoDeVotacaoResquest {
    private Long id;

    @NotEmpty
    private PautaResquest pauta = new PautaResquest();

    @Builder.Default
    private Set<VotoResquest> votos = new LinkedHashSet<>();

    public SessaoDeVotacaoResquest(SessaoDeVotacao sessaoDeVotacao) {
        this.id = sessaoDeVotacao.getId();
        this.pauta = new PautaResquest(sessaoDeVotacao.getPauta());
        this.votos = composeVotosRequest(sessaoDeVotacao.getVotos());
    }

    public static SessaoDeVotacaoResquest converWhithoutPauta(SessaoDeVotacao sessaoDeVotacao) {
        return SessaoDeVotacaoResquest.builder()
                .id(sessaoDeVotacao.getId())
                .votos(composeVotosRequest(sessaoDeVotacao.getVotos()))
                .build();
    }

    private static Set<VotoResquest> composeVotosRequest(Set<Voto> votos) {
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
