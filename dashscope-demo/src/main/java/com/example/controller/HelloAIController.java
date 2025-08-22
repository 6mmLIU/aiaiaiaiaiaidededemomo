package com.example.controller;

import com.example.record.Film;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloAIController {
    @Autowired
    @Qualifier("chatClientBuilder2")
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

    @GetMapping("/helloResponse")
    public Object helloResponse(@RequestParam(value = "msg", required = false, defaultValue = "你是谁") String message) {

        ChatClient.CallResponseSpec call = client.prompt().user(message).call();
        ChatResponse chatResponse = call.chatResponse();
        return chatResponse;
    }

    @GetMapping("helloEntity")
    public Object helloEntity(@RequestParam(value = "msg", required = false, defaultValue = "查询黄渤和他参演的三个电影") String message) {
//        Film film = new Film("mao1", List.of("电影1", "电影2", "电影3"));
//        System.out.println(film.name());
//        System.out.println(film.movies());
//        System.out.println("end");
        Film film=client.prompt().user(message).call().entity(Film.class);
        return film;
    }
}
