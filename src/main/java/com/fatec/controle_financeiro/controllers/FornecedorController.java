package com.fatec.controle_financeiro.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.controle_financeiro.entities.Fornecedor;

@RequestMapping("/api/Fornecedor")
@RestController

public class FornecedorController {
    
    private List<Fornecedor> fornecedores = new ArrayList<>();
    private int proximoId = 1;

    @PostMapping
    public ResponseEntity<Fornecedor> createFornecedor(@RequestBody Fornecedor fornecedor){

        fornecedor.setId(proximoId++);
        fornecedores.add(fornecedor);

        return new ResponseEntity<>(fornecedor, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Fornecedor>> getAllFornecedor(){
            return new ResponseEntity<>(fornecedores, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Fornecedor> getByIdFornecedor(@PathVariable int id){
        for(Fornecedor countFornecedor : fornecedores){
            if(countFornecedor.getId() == id){
                return new ResponseEntity<>(countFornecedor, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("{id}")
    public ResponseEntity<Fornecedor> updateFornecedor(@PathVariable int id, @RequestBody Fornecedor entityFornecedor){
        for(Fornecedor fornecedor : fornecedores){
            if(entityFornecedor.getId() == id){
                fornecedor.setId(entityFornecedor.getId());
                fornecedor.setNome(entityFornecedor.getNome());
                return new ResponseEntity<>(fornecedor, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(entityFornecedor, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable int id){
        for(Fornecedor fornecedor : fornecedores){
            if(fornecedor.getId() == id){
                fornecedores.remove(fornecedor);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //CREATE, READ, UPDATE E DELETE
}
