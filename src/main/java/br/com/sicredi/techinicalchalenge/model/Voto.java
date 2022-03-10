package br.com.sicredi.techinicalchalenge.model;

import br.com.sicredi.techinicalchalenge.model.enums.TipoValorVoto;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String valorVoto;

    @OneToOne
    private Associado autor;

    @ManyToOne
    @JoinColumn(name = "sessao_de_votacao_id")
    private SessaoDeVotacao sessaoDeVotacao;


    public TipoValorVoto getValorVoto() {
        return TipoValorVoto.getValue(valorVoto);
    }

    public void setValorVoto(TipoValorVoto valorVoto){
        this.valorVoto = valorVoto.getValue();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Voto voto = (Voto) o;
        return autor.equals(voto.autor) && sessaoDeVotacao.equals(voto.sessaoDeVotacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(autor, sessaoDeVotacao);
    }
}

