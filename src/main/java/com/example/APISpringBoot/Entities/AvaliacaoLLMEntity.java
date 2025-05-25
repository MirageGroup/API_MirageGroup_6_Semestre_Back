package com.example.APISpringBoot.Entities;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "llm_comparisons")
public class AvaliacaoLLMEntity {
    @Id
    private String id;
    private String idResposta;
    private String model;
    private LocalDateTime data;
    private double AvaliacaoMedia;
    private String feedback;
    private List<ParametroAvaliativo> parametros;
    private String uuid;
}