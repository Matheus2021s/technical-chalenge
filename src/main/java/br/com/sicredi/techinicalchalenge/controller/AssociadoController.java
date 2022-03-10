package br.com.sicredi.techinicalchalenge.controller;

import br.com.sicredi.techinicalchalenge.dto.associado.AssociadoResponse;
import br.com.sicredi.techinicalchalenge.dto.associado.AssociadoResquest;
import br.com.sicredi.techinicalchalenge.dto.associado.AssociadoUpdateResquest;
import br.com.sicredi.techinicalchalenge.model.Associado;
import br.com.sicredi.techinicalchalenge.service.AssociadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/associado")
public class AssociadoController {

    private final AssociadoService associadoService;

    public AssociadoController(AssociadoService associadoService) {
        this.associadoService = associadoService;
    }

    @GetMapping
    public ResponseEntity<List<AssociadoResponse>> findAll(){
        return ResponseEntity.ok(AssociadoResponse.toListResponse(this.associadoService.findAll()));
    }

    @GetMapping("{associadoId}")
    public ResponseEntity<AssociadoResponse> findById(@PathVariable Long associadoId){
        Optional<Associado> optional = this.associadoService.findById(associadoId);
        if (optional.isPresent()){
            return ResponseEntity.ok(new AssociadoResponse(optional.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<AssociadoResponse> create(@RequestBody AssociadoResquest associadoResquest) throws URISyntaxException {
        Associado associado = this.associadoService.create(associadoResquest.convert());
        URI uri = new URI("/associado/"+associado.getId());
        return ResponseEntity.created(uri).body(new AssociadoResponse(associado));
    }


    @PutMapping
    public ResponseEntity<AssociadoResponse> update(@RequestBody AssociadoUpdateResquest associadoUpdateResquest){
        Associado newData = associadoUpdateResquest.convert();
        Optional<Associado> optional = this.associadoService.findById(newData.getId());
        if (optional.isPresent()){
            Associado olderData = optional.get();
            this.associadoService.update(olderData,newData);
            return ResponseEntity.ok(new AssociadoResponse(olderData));
        }
        return  ResponseEntity.notFound().build();
    }


    @DeleteMapping("{associadoId}")
    public ResponseEntity<?> delete(@PathVariable Long associadoId){
        Optional<Associado> optional = this.associadoService.findById(associadoId);
        if (optional.isPresent()){
            this.associadoService.delete(associadoId);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
