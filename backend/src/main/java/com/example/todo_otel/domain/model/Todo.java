package com.example.todo_otel.domain.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Todo {
    private String id;
    private String title;
    private String description;
    private LocalDateTime expire;
    private boolean completed;
}
