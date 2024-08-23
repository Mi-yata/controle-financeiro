package com.fatec.exemplo_aula01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExercicioExemplos1 {
    
    @GetMapping("/contar-letras/{texto}")
    public String contaLetras(){
        int tamanho = .length();
        return String.valueOf(tamanho);
    }

    @GetMapping("/idade-com-parametro-tipo-integer/{paramIdade}")
    public String faixa_etaria(){
        int idade = ;
        if(idade < 0 || idade > 120){
            return "Idade invalida";}
            else{
                if (idade < 12) {
                    return "Crianca";
                    }
                else if(idade <= 18){
                    return "Adolescente";
                }
                else if(idade <= 60){
                    return "Adulto";
                }
                else{
                    return "Idoso";
                }
            }
        }
    }

