package com.fatec.controle_financeiro.entities;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class User {
    
        @NotBlank(message = "O nome não pode estar em branco.")
        private String name;

        @NotNull(message = "A idade é obrigatória.")
        @Min(value = 0, message = "A idade não pode ser negativa.")
        private int age;

        // Construtor padrão
        public User() {
        }

        // Getters e Setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }