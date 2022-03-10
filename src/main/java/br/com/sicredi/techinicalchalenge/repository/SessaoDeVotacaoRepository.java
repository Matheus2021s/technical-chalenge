package br.com.sicredi.techinicalchalenge.repository;

import br.com.sicredi.techinicalchalenge.model.SessaoDeVotacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessaoDeVotacaoRepository extends JpaRepository<SessaoDeVotacao,Long> {
}
