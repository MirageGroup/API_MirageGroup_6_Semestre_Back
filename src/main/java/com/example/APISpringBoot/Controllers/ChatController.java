package com.example.APISpringBoot.Controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.APISpringBoot.DTO.ChatDTO;

import io.github.cdimascio.dotenv.Dotenv;
import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin
@RequestMapping("api/v1/chat")
public class ChatController {

    private final WebClient.Builder webClientBuilder;
    private final String predictApiUrl;

    public ChatController(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;

        Dotenv dotenv = Dotenv.load();
        this.predictApiUrl = dotenv.get("LLM_ENDPOINT");
    }

    @PostMapping(path = "/input")
    public Mono<String> postMethodName(@RequestBody ChatDTO request) {
        String text = request.getText();

        Map<String, String> jsonRequest = new HashMap<>();
        jsonRequest.put("llm_model", "model1");
        jsonRequest.put("text", text);

        return webClientBuilder.build()
                .post()
                .uri(predictApiUrl)
                .bodyValue(jsonRequest)
                .retrieve()
                .bodyToMono(String.class)
                .map(response -> response);
    }
}