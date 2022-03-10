package br.com.sicredi.techinicalchalenge.service;

import br.com.sicredi.techinicalchalenge.model.Associado;
import br.com.sicredi.techinicalchalenge.model.Voto;
import br.com.sicredi.techinicalchalenge.repository.AssociadoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AssociadoService {

    private final AssociadoRepository associadoRepository;

    public AssociadoService(AssociadoRepository associadoRepository) {
        this.associadoRepository = associadoRepository;
    }


    public Associado findById(Long id){
        Optional<Associado> optional = this.associadoRepository.findById(id);
        if (optional.isPresent()){
            return optional.get();
        }
        throw  new IllegalArgumentException("Associado Not found");
    }

    @Transactional
    public Associado create (Associado associado){
        return this.associadoRepository.save(associado);
    }


    @Transactional
    public Voto Associado(Associado newObjectData) {
        Optional<Associado> optional = this.associadoRepository.findById(newObjectData.getId());
        if ( optional.isPresent() ) {
            Associado associado = optional.get();
        }
        throw  new IllegalArgumentException("Associado Not found");
    }

    @Transactional
    public Associado delete(Associado associado){
        Optional<Associado> optional = this.associadoRepository.findById(associado.getId());
        if ( optional.isPresent() ) {
            this.associadoRepository.delete(optional.get());
        }
        throw  new IllegalArgumentException("Associado Not found");
    }
}
