package hn.dz.AgentSpring.controllers;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class StreamAgentController {

    private ChatClient chatClient;

    public StreamAgentController(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.defaultAdvisors(new SimpleLoggerAdvisor()).build();
    }
    @GetMapping("/streamChat")
    public Flux<String> streamChat(String message){
        return chatClient.prompt()

                          .user(message)
                          .stream()
                          .content();
    }
    @GetMapping("/nostreamChat")
    public String nostreamChat(String message){
        return chatClient.prompt()

                .user(message)
                .call()
                .content();
    }
}
