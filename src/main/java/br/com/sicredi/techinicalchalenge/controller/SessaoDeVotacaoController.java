package br.com.sicredi.techinicalchalenge.controller;


import br.com.sicredi.techinicalchalenge.dto.sessao_de_votacao.SessaoDeVotacaoResponse;
import br.com.sicredi.techinicalchalenge.dto.sessao_de_votacao.SessaoDeVotacaoResquest;
import br.com.sicredi.techinicalchalenge.dto.sessao_de_votacao.SessaoDeVotacaoUpdateResquest;
import br.com.sicredi.techinicalchalenge.model.SessaoDeVotacao;
import br.com.sicredi.techinicalchalenge.service.SessaoDeVotacaoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("sessao-de-votacao")
public class SessaoDeVotacaoController {

    public static final Logger LOGGER = LogManager.getLogger( SessaoDeVotacaoController.class.getName() );

    private final SessaoDeVotacaoService sessaoDeVotacaoService;

    public SessaoDeVotacaoController(SessaoDeVotacaoService sessaoDeVotacaoService) {
        this.sessaoDeVotacaoService = sessaoDeVotacaoService;
    }

    @GetMapping
    public ResponseEntity<List<SessaoDeVotacaoResponse>> findAll(RequestEntity requestEntity){

        LOGGER.info( "Request: " + requestEntity);

        ResponseEntity<List<SessaoDeVotacaoResponse>> ok = ResponseEntity.ok( SessaoDeVotacaoResponse.toListResponse( this.sessaoDeVotacaoService.findAll() ) );

        LOGGER.info( "Response:  " + ok );

        return ok;
    }

    @GetMapping("{sessaoDeVotacaoId}")
    public ResponseEntity<SessaoDeVotacaoResponse> findById(@PathVariable Long sessaoDeVotacaoId, RequestEntity requestEntity){

        LOGGER.info("Request: " + requestEntity);

        Optional<SessaoDeVotacao> optional = this.sessaoDeVotacaoService.findById( sessaoDeVotacaoId );

        if (optional.isPresent()){

            ResponseEntity<SessaoDeVotacaoResponse> ok = ResponseEntity.ok( new SessaoDeVotacaoResponse( optional.get() ) );

            LOGGER.info( "Response:  " + ok );

            return ok;
        }

        ResponseEntity notFound = ResponseEntity.notFound().build();

        LOGGER.info( "Response:  " +  notFound );

        return notFound;
    }

    @PostMapping
    public ResponseEntity<SessaoDeVotacaoResponse> create(@RequestBody SessaoDeVotacaoResquest sessaoDeVotacaoResquest, RequestEntity requestEntity) throws URISyntaxException {

        LOGGER.info( "Request: " + requestEntity );
        LOGGER.info( "Body: " + sessaoDeVotacaoResquest.toString() );


        SessaoDeVotacao sessaoDeVotacao = this.sessaoDeVotacaoService.create( sessaoDeVotacaoResquest.convert() );

        URI uri = new URI("/sessao-de-Votacao/" + sessaoDeVotacao.getId() );

        ResponseEntity<SessaoDeVotacaoResponse> created = ResponseEntity.created( uri ).body( new SessaoDeVotacaoResponse( sessaoDeVotacao ) );

        LOGGER.info( "Response:  " + created );

        return created;
    }


    @PutMapping
    public ResponseEntity<SessaoDeVotacaoResponse> update(@RequestBody SessaoDeVotacaoUpdateResquest sessaoDeVotacaoUpdateResquest, RequestEntity requestEntity){

        LOGGER.info( "Request: " + requestEntity );
        LOGGER.info( "Body: " + sessaoDeVotacaoUpdateResquest.toString() );


        SessaoDeVotacao newData = sessaoDeVotacaoUpdateResquest.convert();

        Optional<SessaoDeVotacao> optional = this.sessaoDeVotacaoService.findById( newData.getId() );

        if ( optional.isPresent() ){

            SessaoDeVotacao olderData = optional.get();

            this.sessaoDeVotacaoService.update( olderData , newData );

            ResponseEntity<SessaoDeVotacaoResponse> ok = ResponseEntity.ok( new SessaoDeVotacaoResponse( olderData ) );

            LOGGER.info( "Response:  " + ok );

            return ok;

        }

        ResponseEntity notFound = ResponseEntity.notFound().build();

        LOGGER.info( "Response:  " +  notFound );

        return notFound;
    }


    @DeleteMapping("{sessaoDeVotacaoId}")
    public ResponseEntity<?> delete(@PathVariable Long sessaoDeVotacaoId,  RequestEntity requestEntity){

        LOGGER.info( "Request: " + requestEntity );

        Optional<SessaoDeVotacao> optional = this.sessaoDeVotacaoService.findById( sessaoDeVotacaoId );

        if (optional.isPresent()){

            this.sessaoDeVotacaoService.delete(sessaoDeVotacaoId);

            ResponseEntity noContent = ResponseEntity.noContent().build();

            LOGGER.info( "Response:  " +  noContent );

            return noContent;

        }

        ResponseEntity notFound = ResponseEntity.notFound().build();

        LOGGER.info( "Response:  " +  notFound );

        return notFound;
    }

}
