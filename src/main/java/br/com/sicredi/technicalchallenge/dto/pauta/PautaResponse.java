package br.com.sicredi.technicalchallenge.dto.pauta;

import br.com.sicredi.technicalchallenge.dto.sessao_de_votacao.SessaoDeVotacaoResponse;
import br.com.sicredi.technicalchallenge.model.Pauta;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PautaResponse {
    private Long id;
    private String nome;
    private SessaoDeVotacaoResponse sessaoDeVotacao = new SessaoDeVotacaoResponse();

    public PautaResponse(Pauta pauta) {
        this.id = pauta.getId();
        this.nome = pauta.getNome();
        this.sessaoDeVotacao = SessaoDeVotacaoResponse.convertWithoutPauta(pauta.getSessaoDeVotacao());
    }

    public static List<PautaResponse> toListResponse(List<Pauta> listPautas) {
        return listPautas.stream().map(PautaResponse::new).collect(Collectors.toList());
    }

    public Pauta convert() {
        return Pauta.builder()
                .id(getId())
                .nome(getNome())
                .build();
    }
}
