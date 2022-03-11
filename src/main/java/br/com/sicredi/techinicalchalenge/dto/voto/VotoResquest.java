package br.com.sicredi.techinicalchalenge.dto.voto;

import br.com.sicredi.techinicalchalenge.dto.associado.AssociadoResponse;
import br.com.sicredi.techinicalchalenge.dto.associado.AssociadoResquest;
import br.com.sicredi.techinicalchalenge.dto.associado.AssociadoUpdateResquest;
import br.com.sicredi.techinicalchalenge.dto.sessao_de_votacao.SessaoDeVotacaoResquest;
import br.com.sicredi.techinicalchalenge.dto.sessao_de_votacao.SessaoDeVotacaoUpdateResquest;
import br.com.sicredi.techinicalchalenge.model.Associado;
import br.com.sicredi.techinicalchalenge.model.SessaoDeVotacao;
import br.com.sicredi.techinicalchalenge.model.Voto;
import lombok.*;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VotoResquest {
    private Long id;
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
