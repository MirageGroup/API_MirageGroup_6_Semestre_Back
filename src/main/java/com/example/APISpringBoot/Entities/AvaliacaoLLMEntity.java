package com.example.APISpringBoot.Entities;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "avaliacoes")
public class AvaliacaoLLMEntity {
    @Id
    private String id;
    private String llm;
    private LocalDateTime data;
    private int avaliacaoGeral;
    private String comentario;
    private List<ParametroAvaliativo> parametros;

}