package br.com.sicredi.technicalchallenge.dto.voto;

import br.com.sicredi.technicalchallenge.dto.associado.AssociadoResquest;
import br.com.sicredi.technicalchallenge.dto.sessao_de_votacao.SessaoDeVotacaoResquest;
import br.com.sicredi.technicalchallenge.model.Voto;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VotoResquest {
    private Long id;

    @NotEmpty
    private String valorVoto;
    private AssociadoResquest autor = new AssociadoResquest();
    private SessaoDeVotacaoResquest sessaoDeVotacao = new SessaoDeVotacaoResquest();


    public VotoResquest(Voto voto) {
        this.id = voto.getId();
        this.valorVoto = voto.getValorVoto().getValue();
        this.autor = new AssociadoResquest(voto.getAutor());
    }



    public Voto convert() {
        return Voto.builder()
                .id(getId())
                .valorVoto(getValorVoto())
                .autor(getAutor().convert())
                .sessaoDeVotacao(getSessaoDeVotacao().convertWithoutVotos())
                .build();
    }

}
