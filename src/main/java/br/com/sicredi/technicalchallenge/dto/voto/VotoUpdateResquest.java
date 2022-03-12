package br.com.sicredi.technicalchallenge.dto.voto;


import br.com.sicredi.technicalchallenge.dto.associado.AssociadoUpdateResquest;
import br.com.sicredi.technicalchallenge.model.Voto;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VotoUpdateResquest {
    private Long id;
    private String valorVoto;
    private AssociadoUpdateResquest autor = new AssociadoUpdateResquest();


    public VotoUpdateResquest(Voto voto) {
        this.id = voto.getId();
        this.valorVoto = voto.getValorVoto().getValue();
        this.autor = new AssociadoUpdateResquest(voto.getAutor());
    }

    public static List<VotoResponse> toListRequest(List<Voto> listVotos) {
        return listVotos.stream().map(VotoResponse::new).collect(Collectors.toList());
    }

    public Voto convert() {
        return Voto.builder()
                .id(getId())
                .valorVoto(getValorVoto())
                .autor(getAutor().convert())
                .build();
    }
}

