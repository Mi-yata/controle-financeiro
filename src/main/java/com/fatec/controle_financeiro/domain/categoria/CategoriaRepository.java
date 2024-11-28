package com.fatec.controle_financeiro.domain.categoria;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.controle_financeiro.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{
    
}
