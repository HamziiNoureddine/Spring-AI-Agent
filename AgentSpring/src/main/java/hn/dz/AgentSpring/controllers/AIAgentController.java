package hn.dz.AgentSpring.controllers;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.AbstractMessage;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AIAgentController {

     private  ChatClient chatClient;

    public AIAgentController(ChatClient.Builder builder, ChatMemory chatMemory) {

        this.chatClient = builder.
                defaultAdvisors(new SimpleLoggerAdvisor())
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .build();
    }
    @GetMapping("/chat")
    public String chat (String message){
        List<Message> messages = List.of(new UserMessage("hamzi"),
                new AssistantMessage("HAMZI"));
     return chatClient.prompt()
             .system("write all response in arabic")
             .messages( messages)
             .user(message)
             .call()
             .content();
    }

}
