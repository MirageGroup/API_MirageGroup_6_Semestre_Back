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
    private String model;
    private LocalDateTime data;
    private double AvaliacaoMedia;
    private String feedback;
    private List<ParametroAvaliativo> parametros;
    private double tempoRespostaMs;

public double  getTempoRespostaMs() {
    return tempoRespostaMs;
}

public void setTempoRespostaMs(double  tempoRespostaMs) {
    this.tempoRespostaMs = tempoRespostaMs;
}
public String getModelo() {
    return model;
}

public void setModelo(String modelo) {
    this.model = modelo;
}

}

