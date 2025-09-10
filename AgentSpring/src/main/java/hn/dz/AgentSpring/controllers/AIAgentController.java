package hn.dz.AgentSpring.controllers;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.client.advisor.api.BaseChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AIAgentController {

     private  ChatClient chatClient;
     List<Message> messageList = List.of(
             new UserMessage("Mon nom est hamzi"),
             new AssistantMessage("Bonjour monsieur Hamzi, maintenant je vous connais."),
             new UserMessage("Qui suis-je ?"),
             new AssistantMessage("أنت السيد Hamzi."));
    public AIAgentController(ChatClient.Builder builder, ChatMemory chatMemory) {

        this.chatClient = builder
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .build();
    }
    @GetMapping("/chat")
    public String chat ( String message){
     return chatClient.prompt()
             .system("أجب دائما باللغة العربية فقط")
             .messages(messageList)
             .user(message)

             .call()
             .content();
    }

}
