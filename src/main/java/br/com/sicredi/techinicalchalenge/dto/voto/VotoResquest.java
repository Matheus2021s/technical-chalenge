package br.com.sicredi.techinicalchalenge.dto.voto;

import br.com.sicredi.techinicalchalenge.model.Voto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class VotoResquest {
    private Long id;

    public VotoResquest(Voto voto) {
        this.id = voto.getId();
    }

    public Voto convert() {
        return Voto.builder()
                .id(getId())
                .build();
    }

}
