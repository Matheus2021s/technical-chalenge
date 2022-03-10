package br.com.sicredi.techinicalchalenge.dto.voto;

import br.com.sicredi.techinicalchalenge.model.Voto;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class VotoResponse {
    private Long id;

    public VotoResponse(Voto voto) {
        this.id = voto.getId();
    }

    public static List<VotoResponse> toListResponse(List<Voto> listVotos) {
        return listVotos.stream().map(VotoResponse::new).collect(Collectors.toList());
    }
}
