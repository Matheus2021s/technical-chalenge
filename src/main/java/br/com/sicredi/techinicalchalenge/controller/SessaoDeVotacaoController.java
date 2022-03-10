package br.com.sicredi.techinicalchalenge.controller;

import br.com.sicredi.techinicalchalenge.dto.pauta.PautaResponse;
import br.com.sicredi.techinicalchalenge.dto.sessao_de_votacao.SessaoDeVotacaoResponse;
import br.com.sicredi.techinicalchalenge.dto.sessao_de_votacao.SessaoDeVotacaoResquest;
import br.com.sicredi.techinicalchalenge.dto.sessao_de_votacao.SessaoDeVotacaoUpdateResquest;
import br.com.sicredi.techinicalchalenge.model.SessaoDeVotacao;
import br.com.sicredi.techinicalchalenge.service.SessaoDeVotacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("sessao-de-votacao")
public class SessaoDeVotacaoController {

    private final SessaoDeVotacaoService sessaoDeVotacaoService;

    public SessaoDeVotacaoController(SessaoDeVotacaoService sessaoDeVotacaoService) {
        this.sessaoDeVotacaoService = sessaoDeVotacaoService;
    }

    @GetMapping
    public ResponseEntity<List<SessaoDeVotacaoResponse>> findAll(){
        return ResponseEntity.ok(SessaoDeVotacaoResponse.toListResponse(this.sessaoDeVotacaoService.findAll()));
    }

    @GetMapping("{sessaoDeVotacaoId}")
    public ResponseEntity<SessaoDeVotacaoResponse> findById(@PathVariable Long sessaoDeVotacaoId){
        Optional<SessaoDeVotacao> optional = this.sessaoDeVotacaoService.findById(sessaoDeVotacaoId);
        if (optional.isPresent()){
            return ResponseEntity.ok(new SessaoDeVotacaoResponse(optional.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<SessaoDeVotacaoResponse> create(@RequestBody SessaoDeVotacaoResquest sessaoDeVotacaoResquest) throws URISyntaxException {
        SessaoDeVotacao sessaoDeVotacao = this.sessaoDeVotacaoService.create(sessaoDeVotacaoResquest.convert());
        URI uri = new URI("/sessao-de-votacao/"+sessaoDeVotacao.getId());
        return ResponseEntity.created(uri).body(new SessaoDeVotacaoResponse(sessaoDeVotacao));
    }


    @PutMapping
    public ResponseEntity<SessaoDeVotacaoResponse> update(@RequestBody SessaoDeVotacaoUpdateResquest sessaoDeVotacaoUpdateResquest){
        SessaoDeVotacao newData = sessaoDeVotacaoUpdateResquest.convert();
        Optional<SessaoDeVotacao> optional = this.sessaoDeVotacaoService.findById(newData.getId());
        if (optional.isPresent()){
            SessaoDeVotacao olderData = optional.get();
            this.sessaoDeVotacaoService.update(olderData,newData);
            return ResponseEntity.ok(new SessaoDeVotacaoResponse(olderData));
        }
        return  ResponseEntity.notFound().build();
    }


    @DeleteMapping("{sessaoDeVotacaoId}")
    public ResponseEntity<?> delete(@PathVariable Long sessaoDeVotacaoId){
        Optional<SessaoDeVotacao> optional = this.sessaoDeVotacaoService.findById(sessaoDeVotacaoId);
        if (optional.isPresent()){
            this.sessaoDeVotacaoService.delete(sessaoDeVotacaoId);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
