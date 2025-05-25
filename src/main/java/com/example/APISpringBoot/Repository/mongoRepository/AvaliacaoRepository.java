package com.example.APISpringBoot.Repository.mongoRepository;

import com.example.APISpringBoot.Entities.AvaliacaoLLMEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AvaliacaoRepository extends MongoRepository<AvaliacaoLLMEntity, String> {

    // This is the correct method
    List<AvaliacaoLLMEntity> findByUuid(String uuid);
}

