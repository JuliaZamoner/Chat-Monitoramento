package com.example.lanchat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

// Indica ao Spring que esta classe contem configuracoes da aplicacao.
@Configuration
// Habilita o suporte a WebSocket com protocolo STOMP para troca de mensagens.
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // Define o prefixo usado pelo servidor para distribuir mensagens aos clientes inscritos.
        registry.enableSimpleBroker("/topic");
        // Define o prefixo que o cliente usa quando envia mensagens para metodos do backend.
        registry.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Cria o endpoint HTTP que sera usado para abrir a conexao WebSocket.
        registry.addEndpoint("/chat")
                // Permite conexoes vindas de diferentes origens, o que facilita o acesso pela rede local.
                .setAllowedOriginPatterns("*")
                // Ativa SockJS como fallback para navegadores ou redes com restricoes no WebSocket puro.
                .withSockJS();
    }
}
