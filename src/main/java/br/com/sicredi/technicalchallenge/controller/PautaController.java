package br.com.sicredi.technicalchallenge.controller;

import br.com.sicredi.technicalchallenge.dto.pauta.PautaResponse;
import br.com.sicredi.technicalchallenge.dto.pauta.PautaResquest;
import br.com.sicredi.technicalchallenge.dto.pauta.PautaUpdateResquest;
import br.com.sicredi.technicalchallenge.model.Pauta;
import br.com.sicredi.technicalchallenge.service.PautaService;
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
@RequestMapping("pauta")
public class PautaController {

    public static final Logger LOGGER = LogManager.getLogger( PautaController.class.getName() );

    private final PautaService pautaService;

    public PautaController(PautaService pautaService) {
        this.pautaService = pautaService;
    }

    @GetMapping
    public ResponseEntity<List<PautaResponse>> findAll( @ApiIgnore RequestEntity requestEntity){

        LOGGER.info( "Request: " + requestEntity);

        ResponseEntity<List<PautaResponse>> ok = ResponseEntity.ok( PautaResponse.toListResponse( this.pautaService.findAll() ) );

        LOGGER.info( "Response:  " + ok );
        
        return ok;
    }

    @GetMapping("{pautaId}")
    public ResponseEntity<PautaResponse> findById(@PathVariable @Valid @NotEmpty Long pautaId, @ApiIgnore RequestEntity requestEntity){

        LOGGER.info("Request: " + requestEntity);

        Optional<Pauta> optional = this.pautaService.findById( pautaId );

        if (optional.isPresent()){

            ResponseEntity<PautaResponse> ok = ResponseEntity.ok( new PautaResponse( optional.get() ) );

            LOGGER.info( "Response:  " + ok );

            return ok;
        }

        ResponseEntity notFound = ResponseEntity.notFound().build();

        LOGGER.info( "Response:  " +  notFound );

        return notFound;
    }

    @PostMapping
    public ResponseEntity<PautaResponse> create(@RequestBody @Valid PautaResquest pautaResquest,  @ApiIgnore RequestEntity requestEntity) throws URISyntaxException {

        LOGGER.info( "Request: " + requestEntity );
        LOGGER.info( "Body: " + pautaResquest.toString() );


        Pauta pauta = this.pautaService.create( pautaResquest.convert() );

        URI uri = new URI("/pauta/" + pauta.getId() );

        ResponseEntity<PautaResponse> created = ResponseEntity.created( uri ).body( new PautaResponse( pauta ) );

        LOGGER.info( "Response:  " + created );

        return created;
    }


    @PutMapping
    public ResponseEntity<PautaResponse> update(@RequestBody @Valid PautaUpdateResquest pautaUpdateResquest, @ApiIgnore RequestEntity requestEntity){

        LOGGER.info( "Request: " + requestEntity );
        LOGGER.info( "Body: " + pautaUpdateResquest.toString() );


        Pauta newData = pautaUpdateResquest.convert();

        Optional<Pauta> optional = this.pautaService.findById( newData.getId() );

        if ( optional.isPresent() ){

            Pauta olderData = optional.get();

            this.pautaService.update( olderData , newData );

            ResponseEntity<PautaResponse> ok = ResponseEntity.ok( new PautaResponse( olderData ) );

            LOGGER.info( "Response:  " + ok );

            return ok;

        }

        ResponseEntity notFound = ResponseEntity.notFound().build();

        LOGGER.info( "Response:  " +  notFound );

        return notFound;
    }


    @DeleteMapping("{pautaId}")
    public ResponseEntity<?> delete(@PathVariable @Valid @NotEmpty Long pautaId, @ApiIgnore RequestEntity requestEntity){

        LOGGER.info( "Request: " + requestEntity );

        Optional<Pauta> optional = this.pautaService.findById( pautaId );

        if (optional.isPresent()){

            this.pautaService.delete(pautaId);

            ResponseEntity noContent = ResponseEntity.noContent().build();

            LOGGER.info( "Response:  " +  noContent );

            return noContent;

        }

        ResponseEntity notFound = ResponseEntity.notFound().build();

        LOGGER.info( "Response:  " +  notFound );

        return notFound;
    }

}
