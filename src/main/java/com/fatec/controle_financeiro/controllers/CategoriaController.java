package com.fatec.controle_financeiro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.controle_financeiro.domain.categoria.CategoriaRepository;
import com.fatec.controle_financeiro.entities.Categoria;

@RequestMapping("/api/Categoria")
@RestController

public class CategoriaController {
    
    @Autowired
    private CategoriaRepository categoriaRepository;

    @PostMapping()
    public ResponseEntity<?> createCategoria(@RequestBody Categoria categoria) {
        try {
            if(categoria.getDescricao().isEmpty()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Descrição não pode ser nula");
            }else{
                if(categoria.isAtivo() == null){
                    categoria.setAtivo(true);
                }
                categoriaRepository.save(categoria);
                return ResponseEntity.status(HttpStatus.CREATED).body("Produto criado com sucesso!");
            }
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Descrição já cadastrada, use uma descrição diferente.");
        }
        }
}
