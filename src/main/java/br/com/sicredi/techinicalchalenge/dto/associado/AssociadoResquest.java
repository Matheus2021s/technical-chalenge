package br.com.sicredi.techinicalchalenge.dto.associado;

import br.com.sicredi.techinicalchalenge.model.Associado;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssociadoResquest {
    private Long id;
    private String cpf;

    public AssociadoResquest(Associado associado) {
        this.id = associado.getId();
        this.cpf = associado.getCpf();
    }

    public Associado convert() {
        return  Associado.builder()
                .id(getId())
                .cpf(getCpf())
                .build();

    }



}
