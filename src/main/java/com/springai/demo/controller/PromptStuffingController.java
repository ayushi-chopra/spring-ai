package com.springai.demo.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PromptStuffingController {

    private final ChatClient chatClient;

    public PromptStuffingController(ChatClient chatClient){
        this.chatClient=chatClient;
    }

    @Value("classpath:/promptTemplates/promptStuffTemplate.st")
    Resource systemMessage;

    @GetMapping("/prompt-stuff")
    public String promptStuff(@RequestParam("message") String message){
//        return this.chatClient.prompt(message).call().content();
        return this.chatClient.prompt()
                 .system(systemMessage)
                 .user(message)
                .call().content();
    }
}
