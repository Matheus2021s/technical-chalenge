package br.com.sicredi.technicalchallenge.dto.associado;

import br.com.sicredi.technicalchallenge.model.Associado;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AssociadoResquest {
    private Long id;

    @NotEmpty
    @CPF(message = "Cpf inválido!")
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
