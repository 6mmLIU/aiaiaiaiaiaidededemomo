package com.example.conf;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.context.annotation.Bean;

public class AIConfiguration {
    @Bean
    public ChatClient chatClientBuilder(ChatClient.Builder builder) {
        return builder.build();
    }

    @Bean
    public ChatClient chatClientModel(ChatModel chatModel) {

        return ChatClient.create(chatModel);
    }
}
