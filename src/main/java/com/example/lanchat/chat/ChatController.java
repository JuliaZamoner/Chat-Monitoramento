package com.example.lanchat.chat;

import java.time.LocalDateTime;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

// Controlador responsável por receber mensagens via WebSocket e reenviar aos clientes conectados.
@Controller
public class ChatController {

    // Escuta mensagens enviadas para /app/send e publica o retorno em /topic/messages.
    @MessageMapping("/send")
    @SendTo("/topic/messages")
    public ChatMessage send(@Payload ChatMessage message) {
        // Garante que o metodo nao falhe caso chegue um payload vazio.
        if (message == null) {
            message = new ChatMessage();
        }

        // Cria um novo objeto com dados tratados antes de reenviar aos usuários.
        ChatMessage sanitized = new ChatMessage();
        // Usa um nome padrão se o remetente vier vazio.
        sanitized.setSender(normalize(message.getSender(), "Anonimo"));
        // Remove espaços extras e evita retornar null no conteúdo.
        sanitized.setContent(normalize(message.getContent(), ""));
        // Registra o horário do lado do servidor.
        sanitized.setSentAt(LocalDateTime.now());
        return sanitized;
    }

    // Normaliza campos de texto, aplicando valor padrão quando necessário.
    private String normalize(String value, String fallback) {
        if (!StringUtils.hasText(value)) {
            return fallback;
        }

        // Remove espaços desnecessários no início e no final do texto.
        return value.trim();
    }
}
