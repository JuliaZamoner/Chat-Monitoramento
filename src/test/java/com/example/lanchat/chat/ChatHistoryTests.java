package com.example.lanchat.chat;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ChatHistoryTests {

    @Test
    void keepsOnlyLastOneHundredMessages() {
        ChatHistory history = new ChatHistory();

        for (int i = 1; i <= 101; i++) {
            ChatMessage message = new ChatMessage();
            message.setSender("Usuario " + i);
            message.setContent("Mensagem " + i);

            history.add(message);
        }

        assertThat(history.findAll()).hasSize(100);
        assertThat(history.findAll().getFirst().getContent()).isEqualTo("Mensagem 2");
        assertThat(history.findAll().getLast().getContent()).isEqualTo("Mensagem 101");
    }
}
