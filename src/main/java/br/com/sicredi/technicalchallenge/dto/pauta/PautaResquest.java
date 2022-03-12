package br.com.sicredi.technicalchallenge.dto.pauta;

import br.com.sicredi.technicalchallenge.model.Pauta;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString@Data
public class PautaResquest {
    private Long id;

    @NotEmpty
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
