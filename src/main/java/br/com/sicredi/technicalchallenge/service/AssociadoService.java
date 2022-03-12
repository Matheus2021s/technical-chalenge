package br.com.sicredi.technicalchallenge.service;

import br.com.sicredi.technicalchallenge.exception.AssociadoNaoEncontradoException;
import br.com.sicredi.technicalchallenge.model.Associado;
import br.com.sicredi.technicalchallenge.model.enums.StatusIsAbleToVote;
import br.com.sicredi.technicalchallenge.repository.AssociadoRepository;
import br.com.sicredi.technicalchallenge.webclient.AbleToVoteClient;
import br.com.sicredi.technicalchallenge.webclient.IsAbleToVoteHerokuResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AssociadoService {

    private final AssociadoRepository associadoRepository;

    private AbleToVoteClient ableToVoteClient;

    public AssociadoService(AssociadoRepository associadoRepository, AbleToVoteClient ableToVoteClient) {
        this.associadoRepository = associadoRepository;
        this.ableToVoteClient = ableToVoteClient;
    }


    public List<Associado> findAll(){
        return this.associadoRepository.findAll();
    }

    public Optional<Associado> findById(Long id){
        return this.associadoRepository.findById(id);
    }

    @Transactional
    public Associado create (Associado associado){
        return this.associadoRepository.save(associado);
    }


    @Transactional
    public void update( Associado olderObjectData ,  Associado newObjectData) {
        olderObjectData.setCpf(newObjectData.getCpf());
    }

    @Transactional
    public void delete(Long associadoId){
        this.associadoRepository.deleteById(associadoId);
    }

    public boolean isAbleToVote(Associado autor) {
        Optional<Associado> optional = this.associadoRepository.findById(autor.getId());
        if (optional.isPresent()){
            Associado associado = optional.get();
            return isAssociadoAbleToVote(associado);
        }
        throw new AssociadoNaoEncontradoException("Associado não encontrado!");
    }

    private boolean isAssociadoAbleToVote(Associado associado) {
        IsAbleToVoteHerokuResponse ableToVote = this.ableToVoteClient.isAbleToVote(associado.getCpf());
        return ableToVote.getStatus().equals(StatusIsAbleToVote.ABLE_TO_VOTE);
    }

}
