package br.com.sicredi.techinicalchalenge.dto.associado;

import br.com.sicredi.techinicalchalenge.model.Associado;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AssociadoResponse {
    private Long id;
    private String cpf;

    public AssociadoResponse(Associado associado) {
        this.id = associado.getId();
        this.cpf = associado.getCpf();
    }

    public static List<AssociadoResponse> toListResponse(List<Associado> listAssociados) {
        return listAssociados.stream().map(AssociadoResponse::new).collect(Collectors.toList());
    }

    public Associado convert() {
        return Associado.builder()
                .id(getId())
                .cpf(getCpf())
                .build();
    }
}
