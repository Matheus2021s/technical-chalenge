package br.com.sicredi.techinicalchalenge.dto.associado;


import br.com.sicredi.techinicalchalenge.dto.associado.AssociadoResponse;
import br.com.sicredi.techinicalchalenge.model.Associado;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class AssociadoUpdateResquest {
    private Long id;
    private String nome;

    public AssociadoUpdateResquest(Associado associado) {
        this.id = associado.getId();
    }

    public static List<AssociadoResponse> toListRequest(List<Associado> listAssociados) {
        return listAssociados.stream().map(AssociadoResponse::new).collect(Collectors.toList());
    }

    public Associado convert() {
        return Associado.builder()
                .id(getId())
                .build();
    }
}

