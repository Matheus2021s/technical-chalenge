package br.com.sicredi.techinicalchalenge.service;

import br.com.sicredi.techinicalchalenge.model.Voto;
import br.com.sicredi.techinicalchalenge.repository.VotoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class VotoService {

    private final VotoRepository votoRepository;

    public VotoService(VotoRepository votoRepository) {
        this.votoRepository = votoRepository;
    }

    public List<Voto> findAll(){
        return this.votoRepository.findAll();
    }

    public Voto findById(Long id){
        Optional<Voto> optional = this.votoRepository.findById(id);
        if (optional.isPresent()){
            return optional.get();
        }
        throw  new IllegalArgumentException("Voto Not found");
    }

    @Transactional
    public Voto create (Voto voto){
        return this.votoRepository.save(voto);
    }


    @Transactional
    public Voto update(Voto newObjectData) {
        Optional<Voto> optional = this.votoRepository.findById(newObjectData.getId());
        if ( optional.isPresent() ) {
            Voto voto = optional.get();
            voto.setValorVoto( newObjectData.getValorVoto());
        }
        throw  new IllegalArgumentException("Voto Not found");
    }

    @Transactional
    public Voto delete(Voto voto){
        Optional<Voto> optional = this.votoRepository.findById(voto.getId());
        if ( optional.isPresent() ) {
            this.votoRepository.delete(optional.get());
        }
        throw  new IllegalArgumentException("Voto Not found");
    }


}
