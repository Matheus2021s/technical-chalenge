package br.com.sicredi.technicalchallenge;

import br.com.sicredi.technicalchallenge.service.AssociadoService;
import br.com.sicredi.technicalchallenge.service.PautaService;
import br.com.sicredi.technicalchallenge.service.SessaoDeVotacaoService;
import br.com.sicredi.technicalchallenge.service.VotoService;
import br.com.sicredi.technicalchallenge.thread.AtualizacaoStatusThread;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableTransactionManagement
@SpringBootApplication
@EnableSwagger2
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
