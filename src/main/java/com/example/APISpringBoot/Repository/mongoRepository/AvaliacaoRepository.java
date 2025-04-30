package com.example.APISpringBoot.Repository.mongoRepository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.APISpringBoot.Entities.AvaliacaoLLMEntity;

public interface AvaliacaoRepository extends MongoRepository<AvaliacaoLLMEntity, String> {
    List<AvaliacaoLLMEntity> findByLlm(String llm);
}