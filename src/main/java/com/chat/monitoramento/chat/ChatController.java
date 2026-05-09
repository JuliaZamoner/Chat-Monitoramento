package com.chat.monitoramento.chat;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// Controlador responsavel por receber mensagens via WebSocket e reenviar aos clientes conectados.
@Controller
public class ChatController {

    private final ChatHistory chatHistory;

    public ChatController(ChatHistory chatHistory) {
        this.chatHistory = chatHistory;
    }

    @GetMapping("/api/messages")
    @ResponseBody
    public List<ChatMessage> history() {
        return chatHistory.findAll();
    }

    // Escuta mensagens enviadas para /app/send e publica o retorno em /topic/messages.
    @MessageMapping("/send")
    @SendTo("/topic/messages")
    public ChatMessage send(@Payload ChatMessage message) {
        // Garante que o metodo nao falhe caso chegue um payload vazio.
        if (message == null) {
            message = new ChatMessage();
        }

        // Cria um novo objeto com dados tratados antes de reenviar aos usuarios.
        ChatMessage sanitized = new ChatMessage();
        // Usa um nome padrao se o remetente vier vazio.
        sanitized.setSender(normalize(message.getSender(), "Anonimo"));
        // Remove espacos extras e evita retornar null no conteudo.
        sanitized.setContent(normalize(message.getContent(), ""));
        // Registra o horario do lado do servidor.
        sanitized.setSentAt(LocalDateTime.now());
        chatHistory.add(sanitized);
        return sanitized;
    }

    // Normaliza campos de texto, aplicando valor padrao quando necessario.
    private String normalize(String value, String fallback) {
        if (!StringUtils.hasText(value)) {
            return fallback;
        }

        // Remove espacos desnecessarios no inicio e no final do texto.
        return value.trim();
    }
}
