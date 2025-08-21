package com.example.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloAIController {
    private ChatClient client;

    public HelloAIController(ChatClient.Builder builder) {
        ChatOptions options = ChatOptions.builder()
                .topP(0.9) //多样性
                .topK(1)    //答案个数
                .build();
        client = builder
                .defaultOptions(options)
                .build();
    }

    @GetMapping("/helloAI")
    public String helloAI(String msg) {
        //  提示功能   用户的提示   调用模型    返回结果的内容部分
        String content = client.prompt().user(msg).call().content();
        return content;
    }
}
