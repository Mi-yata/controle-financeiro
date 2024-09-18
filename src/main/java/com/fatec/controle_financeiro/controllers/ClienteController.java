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

import com.fatec.controle_financeiro.entities.Cliente;

@RequestMapping("/api/Cliente")
@RestController

public class ClienteController {
 
    private List<Cliente> clientes = new ArrayList<>();
    private int proximoId = 1;

    @PostMapping()
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente){

        cliente.setId(proximoId++);
        clientes.add(cliente);

        return new ResponseEntity<>(cliente, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> getAllCliente(){
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Cliente> getById(@PathVariable int id){
        for(Cliente user : clientes){
            if(user.getId()==id){
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable int id, @RequestBody Cliente entity){
        for(Cliente user : clientes){
            if(user.getId() == id){
                user.setId(entity.getId());
                user.setNome(entity.getNome());
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable int id){
        for(Cliente user : clientes){
            if(user.getId() == id){
                clientes.remove(user);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);  
    }

    //CREATE, READ, UPDATE E DELETE

}