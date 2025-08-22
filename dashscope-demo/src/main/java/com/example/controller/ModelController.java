package com.example.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.image.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.StreamHandler;

@RestController
public class ModelController {
    @Autowired
    private ChatModel chatModel;

    @GetMapping("/chatModel1")
    public String chatModel1(String msg) {
        return chatModel.call(msg);
    }

    @GetMapping("chatModel2")
    public String chatModel2(String msg) {
        Prompt prompt = new Prompt(msg);
        ChatResponse call = chatModel.call(prompt);
        Generation result = call.getResult();
        AssistantMessage output = result.getOutput();
        return output.getText();
    }

    @GetMapping("/chatModelStream")
    public void chatModelStream(String msg, HttpServletResponse response) {
        response.setContentType("text/event-stream");
        response.setCharacterEncoding("UTF-8");
        Prompt prompt = new Prompt(msg);
        chatModel.stream((Message) prompt, (Message) new StreamHandler());
    }

    @Autowired
    private ImageModel imageModel;

    @GetMapping("/imageModel")
    public String imageModel(String msg) {
        //图片设定
        ImageOptions options = ImageOptionsBuilder.builder().model("wanx-v1")
                .height(1024)
                .width(1024)
                .build();
        //图片请求
        ImagePrompt imagePrompt = new ImagePrompt(msg, options);
        //调用模型生成结果
        ImageResponse call = imageModel.call(imagePrompt);
        //结果对应的地址
        String url = call.getResult().getOutput().getUrl();
        return url;
    }
}
