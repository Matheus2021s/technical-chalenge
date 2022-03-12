package br.com.sicredi.technicalchallenge.model.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TipoValorVoto {

    SIM("Sim"),
    NAO("Não");

    private String value;


    public String getValue() {
        return value;
    }

    public static TipoValorVoto get(String voto){
        for (TipoValorVoto t : TipoValorVoto.values()){
            if (t.getValue().equals(voto))
                return t;
        }
        throw  new IllegalArgumentException();
    }
}
