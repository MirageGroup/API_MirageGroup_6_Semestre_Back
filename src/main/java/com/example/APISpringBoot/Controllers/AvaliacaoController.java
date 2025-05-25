package com.example.APISpringBoot.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.APISpringBoot.DTO.ChatDTO;
import com.example.APISpringBoot.Entities.AvaliacaoLLMEntity;
import com.example.APISpringBoot.Entities.ParametroAvaliativo;
import com.example.APISpringBoot.Repository.mongoRepository.AvaliacaoRepository;

import io.github.cdimascio.dotenv.Dotenv;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    @PostMapping
    public AvaliacaoLLMEntity criarAvaliacao(@RequestBody AvaliacaoLLMEntity avaliacao) {
    avaliacao.setData(LocalDateTime.now());

    List<ParametroAvaliativo> parametros = avaliacao.getParametros();
    double somaNotas = 0;
    for (ParametroAvaliativo parametro : parametros) {
        somaNotas += parametro.getNota();
    }

    double mediaNotas = parametros.isEmpty() ? 0 : somaNotas / parametros.size();
    avaliacao.setAvaliacaoMedia(mediaNotas);

    return repository.save(avaliacao);
}


    @Autowired
    private AvaliacaoRepository repository;

    private final WebClient.Builder webClientBuilder;
    private final String predictApiUrl;

    public AvaliacaoController(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
        Dotenv dotenv = Dotenv.load();
        this.predictApiUrl = dotenv.get("LLM_ENDPOINT");
    }


    @GetMapping
    public List<AvaliacaoLLMEntity> listarAvaliacoes() {
        return repository.findAll();
    }

    @GetMapping("/{model}")
    public List<AvaliacaoLLMEntity> buscarPorLlm(@PathVariable String llm) {
        return repository.findByModel(llm);
    }

    @GetMapping("/id/{id}")
    public AvaliacaoLLMEntity buscarPorId(@PathVariable String id) {
        return repository.findById(id).orElse(null);
    }


}
