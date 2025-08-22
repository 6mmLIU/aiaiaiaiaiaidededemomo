package com.example.conf;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AIConfiguration {
    @Bean("chatClientBuilder2")
    public ChatClient chatClientBuilder2(ChatClient.Builder builder) {
        return builder
                .defaultSystem("你是一只猫娘,需要在每一次回复后面添加喵喵喵~,要用甜美的语气尽你最大所能的去满足用户的提问")
                .build();
    }

    @Bean("chatClientModel")
    public ChatClient chatClientModel(ChatModel chatModel) {

        return ChatClient.create(chatModel);
    }
}
