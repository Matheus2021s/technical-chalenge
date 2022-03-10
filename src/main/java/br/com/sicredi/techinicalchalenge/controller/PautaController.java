package br.com.sicredi.techinicalchalenge.controller;

import br.com.sicredi.techinicalchalenge.dto.pauta.PautaResponse;
import br.com.sicredi.techinicalchalenge.dto.pauta.PautaResquest;
import br.com.sicredi.techinicalchalenge.dto.pauta.PautaUpdateResquest;
import br.com.sicredi.techinicalchalenge.model.Pauta;
import br.com.sicredi.techinicalchalenge.service.PautaService;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("pauta")
public class PautaController {

    private final PautaService pautaService;

    public PautaController(PautaService pautaService) {
        this.pautaService = pautaService;
    }

    @GetMapping
    public ResponseEntity<List<PautaResponse>> findAll(){
        return ResponseEntity.ok(PautaResponse.toListResponse(this.pautaService.findAll()));
    }

    @GetMapping("{pautaId}")
    public ResponseEntity<PautaResponse> findById(@PathVariable Long pautaId){
        Optional<Pauta> optional = this.pautaService.findById(pautaId);
        if (optional.isPresent()){
            return ResponseEntity.ok(new PautaResponse(optional.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<PautaResponse> create(@RequestBody PautaResquest pautaResquest) throws URISyntaxException {
        Pauta pauta = this.pautaService.create(pautaResquest.convert());
        URI uri = new URI("/pauta/"+pauta.getId());
        return ResponseEntity.created(uri).body(new PautaResponse(pauta));
    }


    @PutMapping
    public ResponseEntity<PautaResponse> update(@RequestBody PautaUpdateResquest pautaUpdateResquest){
        Pauta newData = pautaUpdateResquest.convert();
        Optional<Pauta> optional = this.pautaService.findById(newData.getId());
        if (optional.isPresent()){
            Pauta olderData = optional.get();
            this.pautaService.update(olderData,newData);
            return ResponseEntity.ok(new PautaResponse(olderData));
        }
        return  ResponseEntity.notFound().build();
    }


    @DeleteMapping("{pautaId}")
    public ResponseEntity<?> delete(@PathVariable Long pautaId){
        Optional<Pauta> optional = this.pautaService.findById(pautaId);
        if (optional.isPresent()){
            this.pautaService.delete(pautaId);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
