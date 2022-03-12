package br.com.sicredi.technicalchallenge.model;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Pauta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;


    @OneToOne( cascade = CascadeType.ALL)
    private SessaoDeVotacao sessaoDeVotacao = new SessaoDeVotacao() ;

}
