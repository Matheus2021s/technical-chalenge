package br.com.sicredi.techinicalchalenge.dto.sessao_de_votacao;


import br.com.sicredi.techinicalchalenge.model.SessaoDeVotacao;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SessaoDeVotacaoUpdateResquest {
    private Long id;

    public SessaoDeVotacaoUpdateResquest(SessaoDeVotacao sessaoDeVotacao) {
        this.id = sessaoDeVotacao.getId();
    }

    public static List<SessaoDeVotacaoResponse> toListRequest(List<SessaoDeVotacao> listSessaoDeVotacaos) {
        return listSessaoDeVotacaos.stream().map(SessaoDeVotacaoResponse::new).collect(Collectors.toList());
    }

    public SessaoDeVotacao convert() {
        return SessaoDeVotacao.builder()
                .id(getId())
                .build();
    }
}

