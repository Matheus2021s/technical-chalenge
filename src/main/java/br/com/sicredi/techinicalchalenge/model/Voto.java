package br.com.sicredi.techinicalchalenge.model;

import br.com.sicredi.techinicalchalenge.model.enums.TipoValorVoto;
import lombok.*;

import javax.persistence.*;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String valorVoto;

    @OneToOne
    private Associado autor;



    public TipoValorVoto getValorVoto() {
        return TipoValorVoto.getValue(valorVoto);
    }

    public void setValorVoto(TipoValorVoto valorVoto){
        this.valorVoto = valorVoto.getValue();
    }

}
