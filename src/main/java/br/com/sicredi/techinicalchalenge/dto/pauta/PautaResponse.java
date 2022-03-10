package br.com.sicredi.techinicalchalenge.dto.pauta;

import br.com.sicredi.techinicalchalenge.model.Pauta;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PautaResponse {
    private Long id;
    private String nome;

    public PautaResponse(Pauta pauta) {
        this.id = pauta.getId();
        this.nome = pauta.getNome();
    }

    public static List<PautaResponse> toListResponse(List<Pauta> listPautas) {
        return listPautas.stream().map(PautaResponse::new).collect(Collectors.toList());
    }
}