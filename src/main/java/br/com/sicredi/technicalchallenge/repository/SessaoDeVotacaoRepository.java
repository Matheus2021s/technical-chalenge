package br.com.sicredi.technicalchallenge.repository;

import br.com.sicredi.technicalchallenge.model.SessaoDeVotacao;
import br.com.sicredi.technicalchallenge.model.enums.StatusSessao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessaoDeVotacaoRepository extends JpaRepository<SessaoDeVotacao,Long> {
    List<SessaoDeVotacao> findByStatus(StatusSessao aberta);

}
