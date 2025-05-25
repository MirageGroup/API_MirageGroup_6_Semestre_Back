package com.example.APISpringBoot.Entities;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChatMessage {
    private String role;     // "user" or "assistant"
    private String content;
    private LocalDateTime timestamp;
}
