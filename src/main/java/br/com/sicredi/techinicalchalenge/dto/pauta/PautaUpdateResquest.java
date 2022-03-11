package br.com.sicredi.techinicalchalenge.dto.pauta;


import br.com.sicredi.techinicalchalenge.model.Pauta;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PautaUpdateResquest {
    private Long id;
    private String nome;

    public PautaUpdateResquest(Pauta pauta) {
        this.id = pauta.getId();
        this.nome = pauta.getNome();
    }

    public static List<PautaResponse> toListRequest(List<Pauta> listPautas) {
        return listPautas.stream().map(PautaResponse::new).collect(Collectors.toList());
    }

    public Pauta convert() {
        return Pauta.builder()
                .id(getId())
                .nome(getNome())
                .build();
    }
}

