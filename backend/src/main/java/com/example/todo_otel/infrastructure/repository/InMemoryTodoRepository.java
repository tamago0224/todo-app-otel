package com.example.todo_otel.infrastructure.repository;

import com.example.todo_otel.domain.model.Todo;
import com.example.todo_otel.domain.repository.TodoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryTodoRepository implements TodoRepository {
    private final List<Todo> todos = new ArrayList<>();

    @Override
    public List<Todo> findAll() {
        return new ArrayList<>(todos);
    }

    @Override
    public Optional<Todo> findById(String id) {
        return todos.stream().filter(todo -> todo.getId().equals(id)).findFirst();
    }

    @Override
    public Todo save(Todo todo) {
        deleteById(todo.getId());
        todos.add(todo);
        return todo;
    }

    @Override
    public void deleteById(String id) {
        todos.removeIf(todo -> todo.getId().equals(id));
    }
}
