package br.com.sicredi.techinicalchalenge.service;

import br.com.sicredi.techinicalchalenge.model.Pauta;
import br.com.sicredi.techinicalchalenge.repository.PautaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PautaService {

    private final PautaRepository pautaRepository;

    public PautaService(PautaRepository pautaRepository) {
        this.pautaRepository = pautaRepository;
    }

    public List<Pauta> findAll(){
        return this.pautaRepository.findAll();
    }

    public Optional<Pauta> findById(Long id){
        return this.pautaRepository.findById(id);
    }

    @Transactional
    public Pauta create (Pauta pauta){
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
