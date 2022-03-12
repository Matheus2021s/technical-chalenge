package br.com.sicredi.technicalchallenge.repository;

import br.com.sicredi.technicalchallenge.model.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PautaRepository extends JpaRepository<Pauta,Long> {
}
