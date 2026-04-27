package com.example.lanchat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Marca esta classe como ponto de inicialização da aplicação Spring Boot.
@SpringBootApplication
public class ChatApplication {

    public static void main(String[] args) {
        // Inicia o servidor Spring Boot e carrega todas as configurações da aplicação.
        SpringApplication.run(ChatApplication.class, args);
    }
}
