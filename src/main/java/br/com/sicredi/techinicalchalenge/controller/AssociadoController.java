package br.com.sicredi.techinicalchalenge.controller;

import br.com.sicredi.techinicalchalenge.dto.associado.AssociadoResponse;
import br.com.sicredi.techinicalchalenge.dto.associado.AssociadoResquest;
import br.com.sicredi.techinicalchalenge.dto.associado.AssociadoUpdateResquest;
import br.com.sicredi.techinicalchalenge.model.Associado;
import br.com.sicredi.techinicalchalenge.service.AssociadoService;
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
@RequestMapping("associado")
public class AssociadoController {

    public static final Logger LOGGER = LogManager.getLogger( AssociadoController.class.getName() );

    private final AssociadoService associadoService;

    public AssociadoController(AssociadoService associadoService) {
        this.associadoService = associadoService;
    }

    @GetMapping
    public ResponseEntity<List<AssociadoResponse>> findAll(RequestEntity requestEntity){

        LOGGER.info( "Request: " + requestEntity);

        ResponseEntity<List<AssociadoResponse>> ok = ResponseEntity.ok( AssociadoResponse.toListResponse( this.associadoService.findAll() ) );

        LOGGER.info( "Response:  " + ok );

        return ok;
    }

    @GetMapping("{associadoId}")
    public ResponseEntity<AssociadoResponse> findById(@PathVariable Long associadoId, RequestEntity requestEntity){

        LOGGER.info("Request: " + requestEntity);

        Optional<Associado> optional = this.associadoService.findById( associadoId );

        if (optional.isPresent()){

            ResponseEntity<AssociadoResponse> ok = ResponseEntity.ok( new AssociadoResponse( optional.get() ) );

            LOGGER.info( "Response:  " + ok );

            return ok;
        }

        ResponseEntity notFound = ResponseEntity.notFound().build();

        LOGGER.info( "Response:  " +  notFound );

        return notFound;
    }

    @PostMapping
    public ResponseEntity<AssociadoResponse> create(@RequestBody AssociadoResquest associadoResquest,  RequestEntity requestEntity) throws URISyntaxException {

        LOGGER.info( "Request: " + requestEntity );
        LOGGER.info( "Body: " + associadoResquest.toString() );


        Associado associado = this.associadoService.create( associadoResquest.convert() );

        URI uri = new URI("/associado/" + associado.getId() );

        ResponseEntity<AssociadoResponse> created = ResponseEntity.created( uri ).body( new AssociadoResponse( associado ) );

        LOGGER.info( "Response:  " + created );

        return created;
    }


    @PutMapping
    public ResponseEntity<AssociadoResponse> update(@RequestBody AssociadoUpdateResquest associadoUpdateResquest,  RequestEntity requestEntity){

        LOGGER.info( "Request: " + requestEntity );
        LOGGER.info( "Body: " + associadoUpdateResquest.toString() );


        Associado newData = associadoUpdateResquest.convert();

        Optional<Associado> optional = this.associadoService.findById( newData.getId() );

        if ( optional.isPresent() ){

            Associado olderData = optional.get();

            this.associadoService.update( olderData , newData );

            ResponseEntity<AssociadoResponse> ok = ResponseEntity.ok( new AssociadoResponse( olderData ) );

            LOGGER.info( "Response:  " + ok );

            return ok;

        }

        ResponseEntity notFound = ResponseEntity.notFound().build();

        LOGGER.info( "Response:  " +  notFound );

        return notFound;
    }


    @DeleteMapping("{associadoId}")
    public ResponseEntity<?> delete(@PathVariable Long associadoId,  RequestEntity requestEntity){

        LOGGER.info( "Request: " + requestEntity );

        Optional<Associado> optional = this.associadoService.findById( associadoId );

        if (optional.isPresent()){

            this.associadoService.delete(associadoId);

            ResponseEntity noContent = ResponseEntity.noContent().build();

            LOGGER.info( "Response:  " +  noContent );

            return noContent;

        }

        ResponseEntity notFound = ResponseEntity.notFound().build();

        LOGGER.info( "Response:  " +  notFound );

        return notFound;
    }

}
