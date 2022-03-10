package br.com.sicredi.techinicalchalenge.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoValorVoto {

    SIM("Sim"),
    NAO("Não");

    private String value;

    public static TipoValorVoto getValue(String voto){
        for (TipoValorVoto t : TipoValorVoto.values()){
            if (t.getValue().equals(voto))
                return t;
        }
        throw  new IllegalArgumentException();
    }
}
