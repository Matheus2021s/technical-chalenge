package br.com.sicredi.techinicalchalenge.dto.pauta;

import br.com.sicredi.techinicalchalenge.model.Pauta;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PautaResquest {
    private Long id;
    private String nome;

    public PautaResquest(Pauta pauta) {
        this.id = pauta.getId();
        this.nome = pauta.getNome();
    }

    public Pauta convert() {
        return Pauta.builder()
                .id(getId())
                .nome(getNome())
                .build();
    }

}
