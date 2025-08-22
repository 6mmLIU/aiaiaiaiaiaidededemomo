package com.example;

import ch.qos.logback.core.net.server.Client;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloDeepSeekController {
    private ChatClient client;
    public HelloDeepSeekController(ChatClient.Builder builder){
        ChatOptions options = ChatOptions.builder()
                .topP(0.9) //多样性
                .topK(1)    //答案个数
                .build();
        client = builder
                .defaultOptions(options)
                .build();
    }
    @GetMapping("/hello")
    public String hello(String msg){
        String content = client.prompt().user(msg).call().content();
        return content;
    }
}
