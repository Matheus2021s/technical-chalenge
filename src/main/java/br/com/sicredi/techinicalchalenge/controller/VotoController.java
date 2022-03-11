package br.com.sicredi.techinicalchalenge.controller;

import br.com.sicredi.techinicalchalenge.dto.voto.VotoResponse;
import br.com.sicredi.techinicalchalenge.dto.voto.VotoResquest;
import br.com.sicredi.techinicalchalenge.dto.voto.VotoUpdateResquest;
import br.com.sicredi.techinicalchalenge.model.Voto;
import br.com.sicredi.techinicalchalenge.service.AssociadoService;
import br.com.sicredi.techinicalchalenge.service.VotoService;
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
@RequestMapping("voto")
public class VotoController {

    public static final Logger LOGGER = LogManager.getLogger( VotoController.class.getName() );

    private final VotoService votoService;

    private final AssociadoService associadoService;

    public VotoController(VotoService votoService, AssociadoService associadoService) {
        this.votoService = votoService;
        this.associadoService = associadoService;
    }

    @GetMapping
    public ResponseEntity<List<VotoResponse>> findAll(RequestEntity requestEntity){

        LOGGER.info( "Request: " + requestEntity);

        ResponseEntity<List<VotoResponse>> ok = ResponseEntity.ok( VotoResponse.toListResponse( this.votoService.findAll() ) );

        LOGGER.info( "Response:  " + ok );

        return ok;
    }

    @GetMapping("{votoId}")
    public ResponseEntity<VotoResponse> findById(@PathVariable Long votoId, RequestEntity requestEntity){

        LOGGER.info("Request: " + requestEntity);

        Optional<Voto> optional = this.votoService.findById( votoId );

        if (optional.isPresent()){

            ResponseEntity<VotoResponse> ok = ResponseEntity.ok( new VotoResponse( optional.get() ) );

            LOGGER.info( "Response:  " + ok );

            return ok;
        }

        ResponseEntity notFound = ResponseEntity.notFound().build();

        LOGGER.info( "Response:  " +  notFound );

        return notFound;
    }

    @PostMapping
    public ResponseEntity<VotoResponse> create(@RequestBody VotoResquest votoResquest,  RequestEntity requestEntity) throws URISyntaxException {

        LOGGER.info( "Request: " + requestEntity );
        LOGGER.info( "Body: " + votoResquest.toString() );

        Voto voto = votoResquest.convert();

        if ( this.associadoService.isAbleToVote( voto.getAutor() ) ){

            Voto votoCreated = this.votoService.create(voto);

            URI uri = new URI("/voto/" + votoCreated.getId() );

            ResponseEntity<VotoResponse> created = ResponseEntity.created( uri ).body( new VotoResponse( votoCreated ) );

            LOGGER.info( "Response:  " + created );

            return created;
        }
        
        ResponseEntity notFound = ResponseEntity.notFound().build();

        LOGGER.info( "Response:  " +  notFound );

        return notFound;
    }


    @PutMapping
    public ResponseEntity<VotoResponse> update(@RequestBody VotoUpdateResquest votoUpdateResquest,  RequestEntity requestEntity){

        LOGGER.info( "Request: " + requestEntity );
        LOGGER.info( "Body: " + votoUpdateResquest.toString() );


        Voto newData = votoUpdateResquest.convert();

        Optional<Voto> optional = this.votoService.findById( newData.getId() );

        if ( optional.isPresent() ){

            Voto olderData = optional.get();

            this.votoService.update( olderData , newData );

            ResponseEntity<VotoResponse> ok = ResponseEntity.ok( new VotoResponse( olderData ) );

            LOGGER.info( "Response:  " + ok );

            return ok;

        }

        ResponseEntity notFound = ResponseEntity.notFound().build();

        LOGGER.info( "Response:  " +  notFound );

        return notFound;
    }


    @DeleteMapping("{votoId}")
    public ResponseEntity<?> delete(@PathVariable Long votoId,  RequestEntity requestEntity){

        LOGGER.info( "Request: " + requestEntity );

        Optional<Voto> optional = this.votoService.findById( votoId );

        if (optional.isPresent()){

            this.votoService.delete(votoId);

            ResponseEntity noContent = ResponseEntity.noContent().build();

            LOGGER.info( "Response:  " +  noContent );

            return noContent;

        }

        ResponseEntity notFound = ResponseEntity.notFound().build();

        LOGGER.info( "Response:  " +  notFound );

        return notFound;
    }

}
