package br.com.sicredi.techinicalchalenge.service;

import br.com.sicredi.techinicalchalenge.exception.SessaoDevotacaoInexitenteException;
import br.com.sicredi.techinicalchalenge.exception.SessaoJaVotadaException;
import br.com.sicredi.techinicalchalenge.model.SessaoDeVotacao;
import br.com.sicredi.techinicalchalenge.model.Voto;
import br.com.sicredi.techinicalchalenge.model.enums.TipoValorVoto;
import br.com.sicredi.techinicalchalenge.repository.VotoRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class VotoService {

    private final static Logger LOGGER = LogManager.getLogger(VotoService.class.getName());

    private final VotoRepository votoRepository;

    private SessaoDeVotacaoService sessaoDeVotacaoService;

    private AssociadoService associadoService;

    public VotoService(VotoRepository votoRepository, SessaoDeVotacaoService sessaoDeVotacaoService, AssociadoService associadoService) {
        this.votoRepository = votoRepository;
        this.sessaoDeVotacaoService = sessaoDeVotacaoService;
        this.associadoService = associadoService;
    }


    public List<Voto> findAll(){
        return this.votoRepository.findAll();
    }

    public Optional<Voto> findById(Long id){
        return this.votoRepository.findById(id);
    }

    @Transactional
    public Voto create (Voto voto){
        try {
            Optional<SessaoDeVotacao> optionalSessao = getSessaoDeVotacao(voto);

            if (isSessaoDeVotacaoExists(optionalSessao)){
                SessaoDeVotacao sessaoDeVotacao = optionalSessao.get();

                    if (!isAlreadyVoted(voto, sessaoDeVotacao)) {

                        updateVotoCounters(voto, sessaoDeVotacao);
                        return this.votoRepository.save(voto);

                    } else {
                        throw new SessaoJaVotadaException("O Associado já votou na sessão de votação selecionada!");
                    }

            } else {
                throw new SessaoDevotacaoInexitenteException("Votação selecionada inexistente!");
            }

        } catch (SessaoDevotacaoInexitenteException e) {
             LOGGER.error(e);
        }

        return null;
    }

    private boolean isSessaoDeVotacaoExists(Optional<SessaoDeVotacao> optionalSessao) {
        return optionalSessao.isPresent();
    }

    private boolean isAlreadyVoted(Voto voto, SessaoDeVotacao sessaoDeVotacao) {
        Set<Voto> votos = sessaoDeVotacao.getVotos();
         for (Voto v : votos ) {
            if ( v.getAutor().equals(voto.getAutor()))
                return true;
        }
        return false;
    }

    @Transactional
    void updateVotoCounters(Voto voto, SessaoDeVotacao sessaoDeVotacao) {

        if ( isVotoSim(voto) ) {
            sessaoDeVotacao.setQuantidadeVotosSim( sessaoDeVotacao.getQuantidadeVotosSim() + 1 );
        } else {
            sessaoDeVotacao.setQuantidadeVotosNao( sessaoDeVotacao.getQuantidadeVotosSim() + 1 );
        }
    }

    private boolean isVotoSim(Voto voto) {
        return voto.getValorVoto().equals(TipoValorVoto.SIM);
    }

    private Optional<SessaoDeVotacao> getSessaoDeVotacao(Voto voto) {
        return this.sessaoDeVotacaoService.findById(voto.getSessaoDeVotacao().getId());
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
