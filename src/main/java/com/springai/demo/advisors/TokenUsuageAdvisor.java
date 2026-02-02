package com.springai.demo.advisors;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.api.CallAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAdvisorChain;
import org.springframework.ai.chat.metadata.Usage;

public class TokenUsuageAdvisor implements CallAdvisor {

    private static final Logger log= LoggerFactory.getLogger(TokenUsuageAdvisor.class);

    @Override
    public ChatClientResponse adviseCall(ChatClientRequest chatClientRequest, CallAdvisorChain callAdvisorChain) {
        ChatClientResponse chatClientResponse = callAdvisorChain.nextCall(chatClientRequest);
        Usage usage = chatClientResponse.chatResponse().getMetadata().getUsage();
        if(usage!=null){
            log.info("total tokens used in process"+usage.getCompletionTokens());
        }
        return chatClientResponse;
    }

    @Override
    public String getName() {
        return "TokenUsuageAdvisor";
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
