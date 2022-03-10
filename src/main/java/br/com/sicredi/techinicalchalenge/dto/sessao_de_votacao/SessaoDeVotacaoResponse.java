package br.com.sicredi.techinicalchalenge.dto.sessao_de_votacao;

import br.com.sicredi.techinicalchalenge.model.SessaoDeVotacao;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class SessaoDeVotacaoResponse {
    private Long id;

    public SessaoDeVotacaoResponse(SessaoDeVotacao sessaoDeVotacao) {
        this.id = sessaoDeVotacao.getId();
    }

    public static List<SessaoDeVotacaoResponse> toListResponse(List<SessaoDeVotacao> listSessaoDeVotacaos) {
        return listSessaoDeVotacaos.stream().map(SessaoDeVotacaoResponse::new).collect(Collectors.toList());
    }
}
