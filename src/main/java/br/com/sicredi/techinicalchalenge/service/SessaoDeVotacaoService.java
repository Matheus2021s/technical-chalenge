package br.com.sicredi.techinicalchalenge.service;

import br.com.sicredi.techinicalchalenge.repository.SessaoDeVotacaoRepository;
import org.springframework.stereotype.Service;

@Service
public class SessaoDeVotacaoService {

    private final SessaoDeVotacaoRepository sessaoDeVotacaoRepository;

    public SessaoDeVotacaoService(SessaoDeVotacaoRepository sessaoDeVotacaoRepository) {
        this.sessaoDeVotacaoRepository = sessaoDeVotacaoRepository;
    }
}
