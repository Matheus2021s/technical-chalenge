package br.com.sicredi.techinicalchalenge.thread;

import br.com.sicredi.techinicalchalenge.model.SessaoDeVotacao;
import br.com.sicredi.techinicalchalenge.model.Voto;
import br.com.sicredi.techinicalchalenge.model.enums.StatusSessao;
import br.com.sicredi.techinicalchalenge.model.enums.TipoValorVoto;
import br.com.sicredi.techinicalchalenge.repository.SessaoDeVotacaoRepository;
import br.com.sicredi.techinicalchalenge.repository.VotoRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Component
public class AtualizacaoStatusThread implements Runnable {

    private SessaoDeVotacaoRepository sessaoDeVotacaoRepository;
    private VotoRepository votoRepository;

    public AtualizacaoStatusThread( SessaoDeVotacaoRepository sessaoDeVotacaoRepository,  VotoRepository votoRepository) {
        this.sessaoDeVotacaoRepository = sessaoDeVotacaoRepository;
        this.votoRepository = votoRepository;
    }


    @Override
    public void run() {
        while (true){
            try {
                List<SessaoDeVotacao> lista = getListSessaoDeVotacaoWhithStatusAberta();
                closeSessaoDeVotacaoifExpired(lista);
                threadSleepByAMinute();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void threadSleepByAMinute() throws InterruptedException {
        Thread.sleep(60000);
    }

    private void closeSessaoDeVotacaoifExpired(List<SessaoDeVotacao> lista) {
        if (!lista.isEmpty()){
            for (SessaoDeVotacao s : lista){
                if (isSessaoDeVotacaoExpired(s)){
                    setStatusFechado(s);
                    countVotos(s);
                }
            }
        }
    }

    private void countVotos(SessaoDeVotacao s) {
        int sim = 0;
        int nao = 0;
        List<Voto> list = this.votoRepository.findBySessaoDeVotacao(s);

        for ( Voto v : list){
            if (v.getValorVoto().equals(TipoValorVoto.SIM)){
                sim ++;
            }else {
                nao ++;
            }
        }
        s.setQuantidadeVotosSim(sim);
        s.setQuantidadeVotosNao(nao);

        this.sessaoDeVotacaoRepository.save(s);
    }

    private void setStatusFechado(SessaoDeVotacao s) {
        s.setStatus(StatusSessao.FECHADA);
        this.sessaoDeVotacaoRepository.save(s);
    }

    private boolean isSessaoDeVotacaoExpired(SessaoDeVotacao s) {
        return LocalDateTime.now().isAfter(s.getHorarioFinal());
    }

    private List<SessaoDeVotacao> getListSessaoDeVotacaoWhithStatusAberta() {
        return this.sessaoDeVotacaoRepository.findByStatus(StatusSessao.ABERTA);
    }
}
