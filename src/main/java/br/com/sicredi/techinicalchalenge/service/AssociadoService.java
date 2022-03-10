package br.com.sicredi.techinicalchalenge.service;

import br.com.sicredi.techinicalchalenge.model.Associado;
import br.com.sicredi.techinicalchalenge.repository.AssociadoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AssociadoService {

    private final AssociadoRepository associadoRepository;

    public AssociadoService(AssociadoRepository associadoRepository) {
        this.associadoRepository = associadoRepository;
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
}
