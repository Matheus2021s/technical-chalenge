package br.com.sicredi.techinicalchalenge;

import br.com.sicredi.techinicalchalenge.model.Associado;
import br.com.sicredi.techinicalchalenge.model.Pauta;
import br.com.sicredi.techinicalchalenge.model.SessaoDeVotacao;
import br.com.sicredi.techinicalchalenge.model.Voto;
import br.com.sicredi.techinicalchalenge.model.enums.StatusSessao;
import br.com.sicredi.techinicalchalenge.service.AssociadoService;
import br.com.sicredi.techinicalchalenge.service.PautaService;
import br.com.sicredi.techinicalchalenge.service.SessaoDeVotacaoService;
import br.com.sicredi.techinicalchalenge.service.VotoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.time.LocalDateTime;

@EnableTransactionManagement
@SpringBootApplication
public class TechinicalChalengeApplication implements CommandLineRunner {

    private VotoService votoService;
    private PautaService pautaService;
    private SessaoDeVotacaoService sessaoDeVotacaoService;
    private AssociadoService associadoService;


    public TechinicalChalengeApplication(VotoService votoService, PautaService pautaService, SessaoDeVotacaoService sessaoDeVotacaoService, AssociadoService associadoService) {
        this.votoService = votoService;
        this.pautaService = pautaService;
        this.sessaoDeVotacaoService = sessaoDeVotacaoService;
        this.associadoService = associadoService;
    }

    public static void main(String[] args) {
        SpringApplication.run(TechinicalChalengeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Pauta pauta = this.pautaService.create(
                Pauta.builder()
                        .nome("PAUTA TESTE INICAL")
                        .build());

        SessaoDeVotacao sessaoDeVotacao = this.sessaoDeVotacaoService.create(
                SessaoDeVotacao.builder()
                        .pauta(pauta)
                        .status(StatusSessao.ABERTA)
                        .horarioInicial(LocalDateTime.now())
                        .build());

        Associado associado = this.associadoService.create(
                Associado.builder()
                        .cpf("825.435.080-90")
                        .build());


        this.votoService.create(
                Voto.builder()
                        .autor(associado)
                        .sessaoDeVotacao(sessaoDeVotacao)
                        .valorVoto("Sim")
                        .build());
    }
}
