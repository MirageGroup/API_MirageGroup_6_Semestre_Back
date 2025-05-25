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
import java.util.stream.Collectors;

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

    @PostMapping(path = "/input")
    public Mono<String> postMethodName(@RequestBody ChatDTO request) {
        String text = request.getText();

        Map<String, String> jsonRequest = new HashMap<>();
        jsonRequest.put("llm_model", "model1");
        jsonRequest.put("text", text);

        long start = System.currentTimeMillis();

        return webClientBuilder.build()
                .post()
                .uri(predictApiUrl)
                .bodyValue(jsonRequest)
                .retrieve()
                .bodyToMono(String.class)
                .map(response -> {
                    long end = System.currentTimeMillis();
                    long tempoResposta = end - start;

                    AvaliacaoLLMEntity avaliacao = new AvaliacaoLLMEntity();
                    avaliacao.setModel("model1");
                    avaliacao.setData(LocalDateTime.now());
                    avaliacao.setFeedback(response);
                    avaliacao.setAvaliacaoMedia(0); // você pode calcular se necessário
                    avaliacao.setTempoRespostaMs(tempoResposta);
                    avaliacao.setParametros(null); // ou ajuste se necessário

                    repository.save(avaliacao);

                    return response;
                });
    }

    @GetMapping
    public List<AvaliacaoLLMEntity> listarAvaliacoes() {
        return repository.findAll();
    }



    @GetMapping("/{uuid}")
    public List<AvaliacaoLLMEntity> buscarPorLlm(@PathVariable String uuid) {
        return repository.findByUuid(uuid);

    }

    @GetMapping("/id/{id}")
    public AvaliacaoLLMEntity buscarPorId(@PathVariable String id) {
        return repository.findById(id).orElse(null);
    }

    @GetMapping("/tempo-resposta")
    public List<Map<String, Object>> tempoRespostaPorModelo() {
        List<AvaliacaoLLMEntity> avaliacoes = repository.findAll();

        return avaliacoes.stream()
                .collect(Collectors.groupingBy(AvaliacaoLLMEntity::getModelo))
                .entrySet().stream()
                .map(entry -> {
                    String modelo = entry.getKey();
                    List<AvaliacaoLLMEntity> lista = entry.getValue();
                    double media = lista.stream().mapToLong(AvaliacaoLLMEntity::getTempoRespostaMs).average().orElse(0);
                    Map<String, Object> map = new HashMap<>();
                    map.put("modelo", modelo);
                    map.put("tempoMedioMs", media);
                    return map;
                }).collect(Collectors.toList());
    }
}
