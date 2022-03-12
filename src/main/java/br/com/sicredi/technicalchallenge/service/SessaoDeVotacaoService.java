package br.com.sicredi.technicalchallenge.service;

import br.com.sicredi.technicalchallenge.model.SessaoDeVotacao;
import br.com.sicredi.technicalchallenge.model.enums.StatusSessao;
import br.com.sicredi.technicalchallenge.repository.SessaoDeVotacaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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

    public Optional<SessaoDeVotacao> findById(Long id){
        return this.sessaoDeVotacaoRepository.findById(id);
    }

    @Transactional
    public SessaoDeVotacao create (SessaoDeVotacao sessaoDeVotacao){
        sessaoDeVotacao.setHorarioInicial(LocalDateTime.now());
        sessaoDeVotacao.setHorarioFinal(LocalDateTime.now().plusSeconds(60));
        sessaoDeVotacao.setStatus(StatusSessao.ABERTA);
        return this.sessaoDeVotacaoRepository.save(sessaoDeVotacao);
    }


    @Transactional
    public void update( SessaoDeVotacao olderObjectData ,  SessaoDeVotacao newObjectData) {
        olderObjectData.setHorarioInicial(newObjectData.getHorarioInicial());
        olderObjectData.setHorarioFinal(newObjectData.getHorarioInicial());
        olderObjectData.setStatus(newObjectData.getStatus());
    }

    @Transactional
    public void delete(Long sessaoDeVotacaoId){
        this.sessaoDeVotacaoRepository.deleteById(sessaoDeVotacaoId);
    }
}
