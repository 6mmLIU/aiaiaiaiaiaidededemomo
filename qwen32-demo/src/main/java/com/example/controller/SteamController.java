package com.example.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class SteamController {
    private ChatClient client;
    public SteamController(ChatClient.Builder builder) {
        client = builder.build();
    }

    /**
     * Mono
     * 返回0-1个值
     * Flux
     * 返回 0-n个数据
     *
     * @param msg
     * @return
     */
    @GetMapping("/streammethod")
    public Flux<String> streamMethod(String msg, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        Flux<String> content = client.prompt().user(msg).stream().content();
        return content;
    }
}
