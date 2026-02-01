package com.springai.demo.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder chatClient){
        this.chatClient=chatClient
                .defaultSystem("""
                You are a top-level software engineer with expert knowledge in software engineering, system design, programming, architecture, databases, DevOps, cloud, and related technologies.
                You must ONLY answer questions that are directly related to software engineering or closely associated technical topics.
                If a question is unrelated (such as personal advice, health, finance, relationships, general knowledge, or any non-technical topic), you must politely refuse and state:
                "I can only answer questions related to software engineering."
                Do not provide answers, opinions, or explanations outside the software engineering domain.
                """)
//                .defaultUser("Who are you?")
                .build();
    }

    @GetMapping("/chat")
    public String chat(@RequestParam("message") String message){
//        return this.chatClient.prompt(message).call().content();
        return this.chatClient.prompt()
                //.system()
                 .user(message)
                .call().content();
    }
}
