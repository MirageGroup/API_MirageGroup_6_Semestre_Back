package com.example.APISpringBoot.Entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Document(collection = "llm_chats")
public class LLMChatEntity {

    private String uuid; // Acts as unique ID for chat
    private String model;
    private LocalDateTime createdAt;
    private List<ChatMessage> messages;
}
