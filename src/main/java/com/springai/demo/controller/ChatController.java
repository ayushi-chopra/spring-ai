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
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient chatClient){
        this.chatClient=chatClient;
    }

    @Value("classpath:/promptTemplates/mailPrompt.st")
    Resource mailMessage;

    @GetMapping("/basic-chat")
    public String basicChat(@RequestParam("message") String message){
        return this.chatClient.prompt(message).call().content();
    }

    @GetMapping("/chat")
    public String chat(@RequestParam("customerName") String customerName,
                       @RequestParam("customerMessage") String customerMessage){
//        return this.chatClient.prompt(message).call().content();
        return this.chatClient.prompt()
                 .system("""
                        You are a professional customer service assistant which helps drafting email
                        responses to improve the productivity of the customer support team
                        """)
                 .user(promptUserSpec -> promptUserSpec.text(mailMessage)
                         .param("customerName",customerName)
                         .param("customerMessage",customerMessage))
                .call().content();
    }
}
