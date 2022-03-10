package br.com.sicredi.techinicalchalenge.dto.voto;


import br.com.sicredi.techinicalchalenge.dto.voto.VotoResponse;
import br.com.sicredi.techinicalchalenge.model.Voto;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class VotoUpdateResquest {
    private Long id;
    private String nome;

    public VotoUpdateResquest(Voto voto) {
        this.id = voto.getId();
    }

    public static List<VotoResponse> toListRequest(List<Voto> listVotos) {
        return listVotos.stream().map(VotoResponse::new).collect(Collectors.toList());
    }

    public Voto convert() {
        return Voto.builder()
                .id(getId())
                .build();
    }
}

