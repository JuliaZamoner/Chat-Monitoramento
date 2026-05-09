package com.chat.monitoramento.chat;

import java.time.LocalDateTime;

// Representa a estrutura de uma mensagem enviada e recebida no chat.
public class ChatMessage {

    // Nome do usuario que enviou a mensagem.
    private String sender;
    // Texto digitado pelo usuário.
    private String content;
    // Data e hora em que o servidor processou a mensagem.
    private LocalDateTime sentAt;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }
}
