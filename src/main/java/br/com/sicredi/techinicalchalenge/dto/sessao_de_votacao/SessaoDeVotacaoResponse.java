package br.com.sicredi.techinicalchalenge.dto.sessao_de_votacao;

import br.com.sicredi.techinicalchalenge.dto.pauta.PautaResponse;
import br.com.sicredi.techinicalchalenge.dto.voto.VotoResponse;
import br.com.sicredi.techinicalchalenge.model.Pauta;
import br.com.sicredi.techinicalchalenge.model.SessaoDeVotacao;
import br.com.sicredi.techinicalchalenge.model.Voto;
import br.com.sicredi.techinicalchalenge.model.enums.StatusSessao;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessaoDeVotacaoResponse {
    private Long id;
    private PautaResponse pauta;
    private StatusSessao status;
    private LocalDateTime horarioInicial;
    private LocalDateTime horarioFinal;
    @Builder.Default
    private Set<VotoResponse> votos = new LinkedHashSet<>();

    public SessaoDeVotacaoResponse(SessaoDeVotacao sessaoDeVotacao) {
        this.id = sessaoDeVotacao.getId();
        this.pauta = new PautaResponse(sessaoDeVotacao.getPauta());
        this.status = sessaoDeVotacao.getStatus();
        this.horarioInicial = sessaoDeVotacao.getHorarioInicial();
        this.horarioFinal = sessaoDeVotacao.getHorarioFinal();
        this.votos = composeVotos(sessaoDeVotacao.getVotos());
    }

    private Set<VotoResponse> composeVotos(Set<Voto> votos) {
        return votos.stream().map(VotoResponse::new).collect(Collectors.toSet());
    }

    public static List<SessaoDeVotacaoResponse> toListResponse(List<SessaoDeVotacao> listSessaoDeVotacaos) {
        return listSessaoDeVotacaos.stream().map(SessaoDeVotacaoResponse::new).collect(Collectors.toList());
    }
}
