package br.com.sicredi.technicalchallenge.controller;


import br.com.sicredi.technicalchallenge.dto.sessao_de_votacao.SessaoDeVotacaoResponse;
import br.com.sicredi.technicalchallenge.dto.sessao_de_votacao.SessaoDeVotacaoResquest;
import br.com.sicredi.technicalchallenge.dto.sessao_de_votacao.SessaoDeVotacaoUpdateResquest;
import br.com.sicredi.technicalchallenge.model.SessaoDeVotacao;
import br.com.sicredi.technicalchallenge.service.SessaoDeVotacaoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
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
    public ResponseEntity<List<SessaoDeVotacaoResponse>> findAll( @ApiIgnore RequestEntity requestEntity){

        LOGGER.info( "Request: " + requestEntity);

        ResponseEntity<List<SessaoDeVotacaoResponse>> ok = ResponseEntity.ok( SessaoDeVotacaoResponse.toListResponse( this.sessaoDeVotacaoService.findAll() ) );

        LOGGER.info( "Response:  " + ok );

        return ok;
    }

    @GetMapping("{sessaoDeVotacaoId}")
    public ResponseEntity<SessaoDeVotacaoResponse> findById(@PathVariable @Valid @NotEmpty Long sessaoDeVotacaoId,  @ApiIgnore RequestEntity requestEntity){

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
    public ResponseEntity<SessaoDeVotacaoResponse> create(@RequestBody @Valid SessaoDeVotacaoResquest sessaoDeVotacaoResquest,  @ApiIgnore RequestEntity requestEntity) throws URISyntaxException {

        LOGGER.info( "Request: " + requestEntity );
        LOGGER.info( "Body: " + sessaoDeVotacaoResquest.toString() );


        SessaoDeVotacao sessaoDeVotacao = this.sessaoDeVotacaoService.create( sessaoDeVotacaoResquest.convert() );

        URI uri = new URI("/sessao-de-Votacao/" + sessaoDeVotacao.getId() );

        ResponseEntity<SessaoDeVotacaoResponse> created = ResponseEntity.created( uri ).body( new SessaoDeVotacaoResponse( sessaoDeVotacao ) );

        LOGGER.info( "Response:  " + created );

        return created;
    }


    @PutMapping
    public ResponseEntity<SessaoDeVotacaoResponse> update(@RequestBody @Valid SessaoDeVotacaoUpdateResquest sessaoDeVotacaoUpdateResquest,  @ApiIgnore RequestEntity requestEntity){

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
    public ResponseEntity<?> delete(@PathVariable @Valid @NotEmpty Long sessaoDeVotacaoId, @ApiIgnore RequestEntity requestEntity){

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
