package com.springai.demo.config;

import com.springai.demo.advisors.TokenUsuageAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ChatClientConfig {

    @Bean
    public ChatClient chatClient(ChatClient.Builder chatClient){
        return chatClient
                .defaultAdvisors(List.of(new SimpleLoggerAdvisor(),new TokenUsuageAdvisor()))
                .build();
    }
}
