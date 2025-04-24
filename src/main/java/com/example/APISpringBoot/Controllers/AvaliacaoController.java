package com.example.APISpringBoot.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.APISpringBoot.Entities.AvaliacaoLLMEntity;
import com.example.APISpringBoot.Repository.mongoRepository.AvaliacaoRepository;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoRepository repository;

    @PostMapping
    public AvaliacaoLLMEntity criarAvaliacao(@RequestBody AvaliacaoLLMEntity avaliacao) {
        avaliacao.setData(LocalDateTime.now());
        return repository.save(avaliacao);
    }

    @GetMapping
    public List<AvaliacaoLLMEntity> listarAvaliacoes() {
        return repository.findAll();
    }

    @GetMapping("/{llm}")
    public List<AvaliacaoLLMEntity> buscarPorLlm(@PathVariable String llm) {
        return repository.findByLlm(llm);
    }

    @GetMapping("/id/{id}")
    public AvaliacaoLLMEntity buscarPorId(@PathVariable String id) {
        return repository.findById(id).orElse(null);
    }
}
