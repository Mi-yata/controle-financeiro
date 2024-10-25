package com.fatec.controle_financeiro.controllers;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.controle_financeiro.domain.contasreceber.ContasReceberRepository;
import com.fatec.controle_financeiro.entities.ContasReceber;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;




@RequestMapping("/api/ContasReceber")
@RestController

public class ContasReceberController {
    
    @Autowired
    private ContasReceberRepository contasReceberRepository;

    @PostMapping()
    public ResponseEntity<ContasReceber> createContaReceber(@RequestBody ContasReceber contasreceber) {
        //TODO: process POST request
        ContasReceber contasreceberCreated = contasReceberRepository.save(contasreceber);
        return new ResponseEntity<>(contasreceberCreated, HttpStatus.CREATED);
    }
    
    @GetMapping()
    public ResponseEntity<List<ContasReceber>> getAllContasReceber() {
        List<ContasReceber> contasreceber = contasReceberRepository.findAll();
        return new ResponseEntity<>(contasreceber, HttpStatus.OK);
    }
    
    @GetMapping("{id}")
    public ResponseEntity<ContasReceber> getByIdContasReceber(@PathVariable long id) {
        Optional<ContasReceber> contasreceber = contasReceberRepository.findById(id);
        if(contasreceber.isPresent()){
            return new ResponseEntity<>(contasreceber.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("{id}")
    public ResponseEntity<ContasReceber> updateContaReceber(@PathVariable long id, @RequestBody ContasReceber entityContasReceber) {
        //TODO: process PUT request
        Optional<ContasReceber> contaAtual = contasReceberRepository.findById(id);
        if(contaAtual.isPresent()){
            entityContasReceber.setId(id);
            contasReceberRepository.save(entityContasReceber);
            return new ResponseEntity<>(entityContasReceber, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteContaReceber(@PathVariable long id){
        Optional<ContasReceber> contaAtual = contasReceberRepository.findById(id);
        if(contaAtual.isPresent()){
            contasReceberRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
