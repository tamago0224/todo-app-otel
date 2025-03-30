package com.example.todo_otel.domain.repository;

import com.example.todo_otel.domain.model.Todo;
import java.util.List;
import java.util.Optional;

public interface TodoRepository {
    List<Todo> findAll();
    Optional<Todo> findById(String id);
    Todo save(Todo todo);
    void deleteById(String id);
}
