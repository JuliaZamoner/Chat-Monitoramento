package com.chat.monitoramento.chat;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ChatHistory {

    private static final int MAX_MESSAGES = 100;

    private final List<ChatMessage> messages = new ArrayList<>();

    public synchronized void add(ChatMessage message) {
        messages.add(message);

        if (messages.size() > MAX_MESSAGES) {
            messages.remove(0);
        }
    }

    public synchronized List<ChatMessage> findAll() {
        return List.copyOf(messages);
    }
}
