package br.com.sicredi.techinicalchalenge.service;

import br.com.sicredi.techinicalchalenge.model.Voto;
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

    public Optional<Voto> findById(Long id){
        return this.votoRepository.findById(id);
    }

    @Transactional
    public Voto create (Voto voto){
        return this.votoRepository.save(voto);
    }


    @Transactional
    public void update( Voto olderObjectData ,  Voto newObjectData) {
        olderObjectData.setValorVoto(newObjectData.getValorVoto());

    }

    @Transactional
    public void delete(Long votoId){
        this.votoRepository.deleteById(votoId);
    }

}
