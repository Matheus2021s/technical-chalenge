package br.com.sicredi.technicalchallenge.thread;

import br.com.sicredi.technicalchallenge.model.SessaoDeVotacao;
import br.com.sicredi.technicalchallenge.model.Voto;
import br.com.sicredi.technicalchallenge.model.enums.StatusSessao;
import br.com.sicredi.technicalchallenge.model.enums.TipoValorVoto;
import br.com.sicredi.technicalchallenge.repository.SessaoDeVotacaoRepository;
import br.com.sicredi.technicalchallenge.repository.VotoRepository;
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
                }
            }
        }
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
