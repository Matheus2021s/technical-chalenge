package br.com.sicredi.techinicalchalenge.dto.associado;

import br.com.sicredi.techinicalchalenge.model.Associado;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class AssociadoResquest {
    private Long id;
    private String nome;

    public AssociadoResquest(Associado associado) {
        this.id = associado.getId();
    }

    public Associado convert() {
        return Associado.builder()
                .id(getId())
                .build();
    }

}
