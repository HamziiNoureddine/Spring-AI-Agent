package hn.dz.AgentSpring.controllers;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AIAgentController {

     private  ChatClient chatClient;

    public AIAgentController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }
    @PostMapping("/chat")
    public String chat (@RequestBody String message){
     return chatClient.prompt()
               .user(message)
                .call()
                .content();
    }

}
