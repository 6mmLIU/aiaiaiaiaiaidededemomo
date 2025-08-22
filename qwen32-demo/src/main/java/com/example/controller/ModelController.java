package com.example.controller;

import com.alibaba.cloud.ai.dashscope.api.DashScopeAudioSpeechApi;
import com.alibaba.cloud.ai.dashscope.audio.DashScopeAudioSpeechOptions;
import com.alibaba.cloud.ai.dashscope.audio.synthesis.SpeechSynthesisModel;
import com.alibaba.cloud.ai.dashscope.audio.synthesis.SpeechSynthesisPrompt;
import com.alibaba.cloud.ai.dashscope.audio.synthesis.SpeechSynthesisResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.image.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.logging.StreamHandler;


@RestController
public class ModelController {
//    @Autowired
//    private ChatModel chatModel;
//
//    @GetMapping("/chatModel1")
//    public String chatModel1(String msg) {
//        return chatModel.call(msg);
//    }
//
//    @GetMapping("chatModel2")
//    public String chatModel2(String msg) {
//        Prompt prompt = new Prompt(msg);
//        ChatResponse call = chatModel.call(prompt);
//        Generation result = call.getResult();
//        AssistantMessage output = result.getOutput();
//        return output.getText();
//    }
//
//    @GetMapping("/chatModelStream")
//    public void chatModelStream(String msg, HttpServletResponse response) {
//        response.setContentType("text/event-stream");
//        response.setCharacterEncoding("UTF-8");
//        Prompt prompt = new Prompt(msg);
//        chatModel.stream((Message) prompt, (Message) new StreamHandler());
//    }
//
//    @Autowired
//    private ImageModel imageModel;
//
//    @GetMapping("/imageModel")
//    public void imageModel(String msg, HttpServletResponse response) {
//        // 图片设定
//        ImageOptions options = ImageOptionsBuilder.builder()
//                .model("wanx-v1")
//                .height(1024)
//                .width(1024)
//                .build();
//        // 图片请求
//        ImagePrompt imagePrompt=new ImagePrompt(msg,options);
//        //调用模型生成结果
//        ImageResponse call = imageModel.call(imagePrompt);
//        //结果对应的地址
//        String imageUrl = call.getResult().getOutput().getUrl();
//        try {
//            URL url = URI.create(imageUrl).toURL();
//            InputStream in = url.openStream();
//
//            response.setHeader("Content-Type", MediaType.IMAGE_PNG_VALUE);
//            response.getOutputStream().write(in.readAllBytes());
//            response.getOutputStream().flush();
//        } catch (IOException e) {
//            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//        }

    //    }
    @Autowired
    private SpeechSynthesisModel speechSynthesisModel;
    private static final String PATH = "qwen32-demo/mp3/";

//    //语音转文字
//    @GetMapping("/stt")
//    public String stt() {
//
//    };

    private static final String TEXT = "我是傻逼!!!";

    //文字转语音
    @GetMapping("/tts")
    public String tts() {
        DashScopeAudioSpeechOptions options = DashScopeAudioSpeechOptions.builder()
                .model(DashScopeAudioSpeechApi.AudioSpeechModel.SAM_BERT_ZHICHU_V1.getValue())
                .build();
        SpeechSynthesisPrompt promp = new SpeechSynthesisPrompt(TEXT, options);
        SpeechSynthesisResponse call = speechSynthesisModel.call(promp);
        ByteBuffer audio = call.getResult().getOutput().getAudio();
        File file = new File(PATH + "tts.mp3");
        try (FileOutputStream fos = new FileOutputStream(file);) {
            fos.write(audio.array());
            fos.flush();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return file.getAbsolutePath();
    }

}
