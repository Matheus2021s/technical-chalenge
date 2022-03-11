package br.com.sicredi.techinicalchalenge;

import br.com.sicredi.techinicalchalenge.service.AssociadoService;
import br.com.sicredi.techinicalchalenge.service.PautaService;
import br.com.sicredi.techinicalchalenge.service.SessaoDeVotacaoService;
import br.com.sicredi.techinicalchalenge.service.VotoService;
import br.com.sicredi.techinicalchalenge.thread.AtualizacaoStatusThread;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class TechnicalChallengeApplication implements CommandLineRunner {

    private VotoService votoService;
    private PautaService pautaService;
    private SessaoDeVotacaoService sessaoDeVotacaoService;
    private AssociadoService associadoService;
    private AtualizacaoStatusThread atualizacaoStatusThread;

    public TechnicalChallengeApplication(VotoService votoService, PautaService pautaService, SessaoDeVotacaoService sessaoDeVotacaoService, AssociadoService associadoService, AtualizacaoStatusThread atualizacaoStatusThread) {
        this.votoService = votoService;
        this.pautaService = pautaService;
        this.sessaoDeVotacaoService = sessaoDeVotacaoService;
        this.associadoService = associadoService;
        this.atualizacaoStatusThread = atualizacaoStatusThread;
    }

    public static void main(String[] args) {
        SpringApplication.run(TechnicalChallengeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        this.atualizacaoStatusThread.run();
    }
}
