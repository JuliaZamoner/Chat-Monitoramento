package com.example.lanchat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Marca esta classe como ponto de inicializacao da aplicacao Spring Boot.
@SpringBootApplication
public class LanChatApplication {

    public static void main(String[] args) {
        // Inicia o servidor Spring Boot e carrega todas as configuracoes da aplicacao.
        SpringApplication.run(LanChatApplication.class, args);
    }
}
