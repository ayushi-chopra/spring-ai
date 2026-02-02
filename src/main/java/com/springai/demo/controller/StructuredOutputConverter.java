package com.springai.demo.controller;

import com.springai.demo.model.CountryCities;
import org.jspecify.annotations.Nullable;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.ai.converter.MapOutputConverter;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class StructuredOutputConverter{

    private final ChatClient chatClient;

    public StructuredOutputConverter(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/chat-bean")
    public ResponseEntity<CountryCities> outputBean(@RequestParam("message") String message){
        CountryCities countryCities = this.chatClient.prompt(message)
                .call().entity(CountryCities.class);
        return  ResponseEntity.ok(countryCities);
    }

    @GetMapping("/chat-list")
    public ResponseEntity<List<String>> outputList(@RequestParam("message") String message){
        List<String> countryCities = this.chatClient.prompt(message)
                .call().entity(new ListOutputConverter());
        return  ResponseEntity.ok(countryCities);
    }

    @GetMapping("/chat-map")
    public ResponseEntity<Map<String,Object>> outputMap(@RequestParam("message") String message){
        Map<String, Object> map = this.chatClient.prompt(message)
                .call().entity(new MapOutputConverter());
        return  ResponseEntity.ok(map);
    }

    @GetMapping("/chat-bean-list")
    public ResponseEntity<List<CountryCities>> outputBeanList(@RequestParam("message") String message){
        List<CountryCities> countryCities = this.chatClient.prompt(message)
                .call().entity(new ParameterizedTypeReference<List<CountryCities>>() {
                });
        return  ResponseEntity.ok(countryCities);
    }
}
