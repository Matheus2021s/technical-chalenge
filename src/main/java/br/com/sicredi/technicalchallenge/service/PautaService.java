package br.com.sicredi.technicalchallenge.service;

import br.com.sicredi.technicalchallenge.model.Pauta;
import br.com.sicredi.technicalchallenge.model.SessaoDeVotacao;
import br.com.sicredi.technicalchallenge.repository.PautaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PautaService {

    private final PautaRepository pautaRepository;

    private final  SessaoDeVotacaoService sessaoDeVotacaoService;

    public PautaService(PautaRepository pautaRepository, SessaoDeVotacaoService sessaoDeVotacaoService) {
        this.pautaRepository = pautaRepository;
        this.sessaoDeVotacaoService = sessaoDeVotacaoService;
    }

    public List<Pauta> findAll(){
        return this.pautaRepository.findAll();
    }

    public Optional<Pauta> findById(Long id){
        return this.pautaRepository.findById(id);
    }

    @Transactional
    public Pauta create (Pauta pauta){
        SessaoDeVotacao sessaoDeVotacao = this.sessaoDeVotacaoService.create(new SessaoDeVotacao());
        pauta.setSessaoDeVotacao(sessaoDeVotacao);
        return this.pautaRepository.save(pauta);
    }


    @Transactional
    public void update( Pauta olderObjectData ,  Pauta newObjectData) {
        olderObjectData.setNome(newObjectData.getNome());
    }

    @Transactional
    public void delete(Long pautaId){
        this.pautaRepository.deleteById(pautaId);
    }
}
