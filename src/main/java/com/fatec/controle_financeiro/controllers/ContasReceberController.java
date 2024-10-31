package com.fatec.controle_financeiro.controllers;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.PutMapping;




@RequestMapping("/api/ContasReceber")
@RestController

public class ContasReceberController {
    
    private String msgErro;

    @Autowired
    private ContasReceberRepository contasReceberRepository;

    @PostMapping()
    public ResponseEntity<?> createContaReceber(@RequestBody ContasReceber contasreceber) {
        //TODO: process POST request
        if(contasreceber.getVencimento().isAfter(contasreceber.getEmissao())){
            if((contasreceber.getValor().compareTo(BigDecimal.ZERO) > 0)){
                //Verificar por que está caindo null
                if(contasreceber.getCliente() == null /*|| contaspagar.getCliente() != null*/){
                    ContasReceber contasreceberCreated = contasReceberRepository.save(contasreceber);
                    return new ResponseEntity<>(contasreceberCreated, HttpStatus.CREATED);
                }else{
                    //Cliente nulo
                    msgErro = "Cliente não encontrado";
                    return new ResponseEntity<>(msgErro, HttpStatus.EXPECTATION_FAILED);
                }
            }else{
                //Valor menor que 0
                msgErro += " Valor da conta não pode ser menor que 0";
                return new ResponseEntity<>(msgErro, HttpStatus.EXPECTATION_FAILED);
            }
        }else{
            //Data emissao inválida
            msgErro += " Data de emissão é posterior a de vencimento";
            return new ResponseEntity<>(msgErro, HttpStatus.EXPECTATION_FAILED);
        }
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
    public ResponseEntity<?> updateContaReceber(@PathVariable long id, @RequestBody ContasReceber entityContasReceber) {
        //TODO: process PUT request
        Optional<ContasReceber> contaAtual = contasReceberRepository.findById(id);
        if(contaAtual.isPresent()){
            if(entityContasReceber.getVencimento().isAfter(entityContasReceber.getEmissao())){
                if((entityContasReceber.getValor().compareTo(BigDecimal.ZERO) > 0)){
                    //Verificar por que está caindo null
                    if(entityContasReceber.getCliente() == null /*|| entityContasReceber.getCliente() != null*/){
                        entityContasReceber.setId(id);    
                        ContasReceber contasreceberCreated = contasReceberRepository.save(entityContasReceber);
                        return new ResponseEntity<>(contasreceberCreated, HttpStatus.OK);
                    }else{
                        //Cliente nulo
                        msgErro = "Cliente não encontrado";
                        return new ResponseEntity<>(msgErro, HttpStatus.EXPECTATION_FAILED);
                    }
                }else{
                    //Valor menor que 0
                    msgErro += " Valor da conta não pode ser menor que 0";
                    return new ResponseEntity<>(msgErro, HttpStatus.EXPECTATION_FAILED);
                }
            }else{
                //Data emissao inválida
                msgErro += " Data de emissão é posterior a de vencimento";
                return new ResponseEntity<>(msgErro, HttpStatus.EXPECTATION_FAILED);
            }
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
