package br.com.sicredi.techinicalchalenge.repository;

import br.com.sicredi.techinicalchalenge.model.SessaoDeVotacao;
import br.com.sicredi.techinicalchalenge.model.enums.StatusSessao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessaoDeVotacaoRepository extends JpaRepository<SessaoDeVotacao,Long> {
    List<SessaoDeVotacao> findByStatus(StatusSessao aberta);

}
