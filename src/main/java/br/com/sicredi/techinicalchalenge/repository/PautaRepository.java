package br.com.sicredi.techinicalchalenge.repository;

import br.com.sicredi.techinicalchalenge.model.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PautaRepository extends JpaRepository<Pauta,Long> {
}
