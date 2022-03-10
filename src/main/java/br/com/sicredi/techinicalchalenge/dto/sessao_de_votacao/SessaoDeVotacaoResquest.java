package br.com.sicredi.techinicalchalenge.dto.sessao_de_votacao;

import br.com.sicredi.techinicalchalenge.model.SessaoDeVotacao;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class SessaoDeVotacaoResquest {
    private Long id;

    public SessaoDeVotacaoResquest(SessaoDeVotacao sessaoDeVotacao) {
        this.id = sessaoDeVotacao.getId();
    }

    public SessaoDeVotacao convert() {
        return SessaoDeVotacao.builder()
                .id(getId())
                .build();
    }

}
