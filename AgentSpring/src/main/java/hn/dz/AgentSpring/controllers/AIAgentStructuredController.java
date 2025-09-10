package hn.dz.AgentSpring.controllers;

import hn.dz.AgentSpring.outputStructure.FilmeListe;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AIAgentStructuredController {
    private ChatClient chatClient;
    private ChatMemory chatMemory;

    public AIAgentStructuredController(ChatClient.Builder chatClient, ChatMemory chatMemory) {
        this.chatMemory = chatMemory;
        this.chatClient = chatClient.defaultAdvisors(new SimpleLoggerAdvisor())
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(this.chatMemory).build())
                .build();

    }

    @GetMapping("/askMovie")
    public FilmeListe infoMovie(String message){
        return chatClient.prompt()
                .system("Vous etes un specialiste dans le domaine de la cinema")
                .user(message)
                .call()
                .entity(FilmeListe.class);
    }
}
