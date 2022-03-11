package br.com.sicredi.techinicalchalenge.dto.sessao_de_votacao;


import br.com.sicredi.techinicalchalenge.dto.pauta.PautaUpdateResquest;
import br.com.sicredi.techinicalchalenge.dto.voto.VotoUpdateResquest;
import br.com.sicredi.techinicalchalenge.model.SessaoDeVotacao;
import br.com.sicredi.techinicalchalenge.model.Voto;
import br.com.sicredi.techinicalchalenge.model.enums.StatusSessao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SessaoDeVotacaoUpdateResquest {
    private Long id;
    private PautaUpdateResquest pauta = new PautaUpdateResquest();
    private StatusSessao status;
    private LocalDateTime horarioInicial;
    private LocalDateTime horarioFinal;
    @Builder.Default
    private Set<VotoUpdateResquest> votos = new LinkedHashSet<>();

    public SessaoDeVotacaoUpdateResquest(SessaoDeVotacao sessaoDeVotacao) {
        this.id = sessaoDeVotacao.getId();
    }

    public static List<SessaoDeVotacaoResponse> toListRequest(List<SessaoDeVotacao> listSessaoDeVotacaos) {
        return listSessaoDeVotacaos.stream().map(SessaoDeVotacaoResponse::new).collect(Collectors.toList());
    }

    public SessaoDeVotacao convert() {
        return SessaoDeVotacao.builder()
                .id(getId())
                .pauta(getPauta().convert())
                .status(getStatus())
                .horarioInicial(getHorarioInicial())
                .horarioFinal(getHorarioFinal())
                .votos(composeVotos(getVotos()))
                .build();
    }
    private Set<Voto> composeVotos(Set<VotoUpdateResquest> votos) {
        return votos.stream().map(VotoUpdateResquest::convert).collect(Collectors.toSet());
    }

    public SessaoDeVotacao convertWithoutVotos() {
        return SessaoDeVotacao.builder()
                .id(getId())
                .pauta(getPauta().convert())
                .status(getStatus())
                .horarioInicial(getHorarioInicial())
                .horarioFinal(getHorarioFinal())
                .build();
    }
}

