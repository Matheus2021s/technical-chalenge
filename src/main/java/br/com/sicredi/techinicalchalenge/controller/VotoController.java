package br.com.sicredi.techinicalchalenge.controller;

import br.com.sicredi.techinicalchalenge.dto.voto.VotoResponse;
import br.com.sicredi.techinicalchalenge.dto.voto.VotoResquest;
import br.com.sicredi.techinicalchalenge.dto.voto.VotoUpdateResquest;
import br.com.sicredi.techinicalchalenge.model.Voto;
import br.com.sicredi.techinicalchalenge.service.VotoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("voto")
public class VotoController {

    private final VotoService votoService;

    public VotoController(VotoService votoService) {
        this.votoService = votoService;
    }

    @GetMapping
    public ResponseEntity<List<VotoResponse>> findAll(){
        return ResponseEntity.ok(VotoResponse.toListResponse(this.votoService.findAll()));
    }

    @GetMapping("{votoId}")
    public ResponseEntity<VotoResponse> findById(@PathVariable Long votoId){
        Optional<Voto> optional = this.votoService.findById(votoId);
        if (optional.isPresent()){
            return ResponseEntity.ok(new VotoResponse(optional.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<VotoResponse> create(@RequestBody VotoResquest votoResquest) throws URISyntaxException {
        Voto voto = this.votoService.create(votoResquest.convert());
        URI uri = new URI("/voto/"+voto.getId());
        return ResponseEntity.created(uri).body(new VotoResponse(voto));
    }


    @PutMapping
    public ResponseEntity<VotoResponse> update(@RequestBody VotoUpdateResquest votoUpdateResquest){
        Voto newData = votoUpdateResquest.convert();
        Optional<Voto> optional = this.votoService.findById(newData.getId());
        if (optional.isPresent()){
            Voto olderData = optional.get();
            this.votoService.update(olderData,newData);
            return ResponseEntity.ok(new VotoResponse(olderData));
        }
        return  ResponseEntity.notFound().build();
    }


    @DeleteMapping("{votoId}")
    public ResponseEntity<?> delete(@PathVariable Long votoId){
        Optional<Voto> optional = this.votoService.findById(votoId);
        if (optional.isPresent()){
            this.votoService.delete(votoId);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
