package br.com.sicredi.techinicalchalenge.service;

import br.com.sicredi.techinicalchalenge.model.SessaoDeVotacao;
import br.com.sicredi.techinicalchalenge.model.Voto;
import br.com.sicredi.techinicalchalenge.repository.SessaoDeVotacaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SessaoDeVotacaoService {

    private final SessaoDeVotacaoRepository sessaoDeVotacaoRepository;

    public SessaoDeVotacaoService(SessaoDeVotacaoRepository sessaoDeVotacaoRepository) {
        this.sessaoDeVotacaoRepository = sessaoDeVotacaoRepository;
    }

    public List<SessaoDeVotacao> findAll(){
        return this.sessaoDeVotacaoRepository.findAll();
    }

    public SessaoDeVotacao findById(Long id){
        Optional<SessaoDeVotacao> optional = this.sessaoDeVotacaoRepository.findById(id);
        if (optional.isPresent()){
            return optional.get();
        }
        throw  new IllegalArgumentException("SessaoDeVotacao Not found");
    }

    @Transactional
    public SessaoDeVotacao create (SessaoDeVotacao sessaoDeVotacao){
        return this.sessaoDeVotacaoRepository.save(sessaoDeVotacao);
    }


    @Transactional
    public Voto SessaoDeVotacao(SessaoDeVotacao newObjectData) {
        Optional<SessaoDeVotacao> optional = this.sessaoDeVotacaoRepository.findById(newObjectData.getId());
        if ( optional.isPresent() ) {
            SessaoDeVotacao sessaoDeVotacao = optional.get();
            sessaoDeVotacao.setHorarioInicial( sessaoDeVotacao.getHorarioInicial() );
            sessaoDeVotacao.setHorarioFinal( sessaoDeVotacao.getHorarioFinal() );
            sessaoDeVotacao.setStatus(sessaoDeVotacao.getStatus());
        }
        throw  new IllegalArgumentException("SessaoDeVotacao Not found");
    }

    @Transactional
    public SessaoDeVotacao delete(SessaoDeVotacao sessaoDeVotacao){
        Optional<SessaoDeVotacao> optional = this.sessaoDeVotacaoRepository.findById(sessaoDeVotacao.getId());
        if ( optional.isPresent() ) {
            this.sessaoDeVotacaoRepository.delete(optional.get());
        }
        throw  new IllegalArgumentException("Voto Not found");
    }
}
