package com.fatec.controle_financeiro.controllers;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.controle_financeiro.domain.contaspagar.ContasPagarRepository;
import com.fatec.controle_financeiro.entities.ContasPagar;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;




@RequestMapping("/api/ContasPagar")
@RestController

public class ContasPagarController {
    
    @Autowired
    private ContasPagarRepository contasPagarRepository;

    @PostMapping()
    public ResponseEntity<ContasPagar> createContaPagar(@RequestBody ContasPagar contaspagar) {
        //TODO: process POST request

        if(contaspagar.getVencimento().isAfter(contaspagar.getEmissao()) && (contaspagar.getValor().compareTo(BigDecimal.ZERO) > 0) && contaspagar.getFornecedor() != null){
            ContasPagar contaspagarCreated = contasPagarRepository.save(contaspagar);
            return new ResponseEntity<>(contaspagarCreated, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED) ;
        }
        
    }
    
    @GetMapping()
    public ResponseEntity<List<ContasPagar>> getAllContasPagar() {
        List<ContasPagar> contaspagar = contasPagarRepository.findAll();
        return new ResponseEntity<>(contaspagar, HttpStatus.OK);
    }
    
    @GetMapping("{id}")
    public ResponseEntity<ContasPagar> getByIdContasPagar(@PathVariable long id) {
        Optional<ContasPagar> contaspagar = contasPagarRepository.findById(id);
        if(contaspagar.isPresent()){
            return new ResponseEntity<>(contaspagar.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("{id}")
    public ResponseEntity<ContasPagar> updateContaPagar(@PathVariable long id, @RequestBody ContasPagar entityContasPagar) {
        //TODO: process PUT request
        Optional<ContasPagar> contaAtual = contasPagarRepository.findById(id);
        if(contaAtual.isPresent()){
            if(entityContasPagar.getVencimento().isAfter(entityContasPagar.getEmissao()) && (entityContasPagar.getValor().compareTo(BigDecimal.ZERO) > 0) && entityContasPagar.getFornecedor() != null){
                ContasPagar contaspagarCreated = contasPagarRepository.save(entityContasPagar);
                entityContasPagar.setId(id);
                return new ResponseEntity<>(contaspagarCreated, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED) ;
            }          
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteContaPagar(@PathVariable long id){
        Optional<ContasPagar> contaAtual = contasPagarRepository.findById(id);
        if(contaAtual.isPresent()){
            contasPagarRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}
