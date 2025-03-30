package com.example.todo_otel.application.service;

import com.example.todo_otel.domain.model.Todo;
import com.example.todo_otel.domain.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> listTodos() {
        return todoRepository.findAll();
    }

    public Optional<Todo> getTodoById(String id) {
        return todoRepository.findById(id);
    }

    public Todo createOrUpdateTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public void deleteTodoById(String id) {
        todoRepository.deleteById(id);
    }
}
