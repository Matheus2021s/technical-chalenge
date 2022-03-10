package br.com.sicredi.techinicalchalenge.service;

import br.com.sicredi.techinicalchalenge.model.Pauta;
import br.com.sicredi.techinicalchalenge.model.Voto;
import br.com.sicredi.techinicalchalenge.repository.PautaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PautaService {

    private final PautaRepository pautaRepository;

    public PautaService(PautaRepository pautaRepository) {
        this.pautaRepository = pautaRepository;
    }

    public Pauta findById(Long id){
        Optional<Pauta> optional = this.pautaRepository.findById(id);
        if (optional.isPresent()){
            return optional.get();
        }
        throw  new IllegalArgumentException("Pauta Not found");
    }

    @Transactional
    public Pauta create (Pauta pauta){
        return this.pautaRepository.save(pauta);
    }


    @Transactional
    public Voto Pauta(Pauta newObjectData) {
        Optional<Pauta> optional = this.pautaRepository.findById(newObjectData.getId());
        if ( optional.isPresent() ) {
            Pauta pauta = optional.get();
            pauta.setNome(newObjectData.getNome());
        }
        throw  new IllegalArgumentException("Pauta Not found");
    }

    @Transactional
    public Pauta delete(Pauta pauta){
        Optional<Pauta> optional = this.pautaRepository.findById(pauta.getId());
        if ( optional.isPresent() ) {
            this.pautaRepository.delete(optional.get());
        }
        throw  new IllegalArgumentException("Pauta Not found");
    }
}
