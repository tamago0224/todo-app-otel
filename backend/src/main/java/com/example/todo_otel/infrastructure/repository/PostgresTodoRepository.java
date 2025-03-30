package com.example.todo_otel.infrastructure.repository;

import com.example.todo_otel.domain.model.Todo;
import com.example.todo_otel.domain.repository.TodoRepository;

import jakarta.annotation.Nonnull;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class PostgresTodoRepository implements TodoRepository {
    private final JdbcTemplate jdbcTemplate;

    public PostgresTodoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Todo> todoRowMapper = new RowMapper<>() {
        @Override
        public Todo mapRow(ResultSet rs, int rowNum) throws SQLException {
            Todo todo = new Todo();
            todo.setId(rs.getString("id"));
            todo.setTitle(rs.getString("title"));
            todo.setDescription(rs.getString("description"));
            todo.setExpire(rs.getTimestamp("expire").toLocalDateTime());
            todo.setCompleted(rs.getBoolean("completed"));
            return todo;
        }
    };

    @Override
    public List<Todo> findAll() {
        String sql = "SELECT * FROM todos";
        return jdbcTemplate.query(sql, todoRowMapper);
    }

    @Override
    public Optional<Todo> findById(String id) {
        String sql = "SELECT * FROM todos WHERE id = ?";
        List<Todo> results = jdbcTemplate.query(sql, todoRowMapper, id);
        return results.stream().findFirst();
    }

    @Override
    public Todo save(Todo todo) {
        String sql = "INSERT INTO todos (id, title, description, expire, completed) " +
                     "VALUES (?, ?, ?, ?, ?) " +
                     "ON CONFLICT (id) DO UPDATE SET " +
                     "title = EXCLUDED.title, description = EXCLUDED.description, " +
                     "expire = EXCLUDED.expire, completed = EXCLUDED.completed";
        jdbcTemplate.update(sql, todo.getId(), todo.getTitle(), todo.getDescription(),
                todo.getExpire(), todo.isCompleted());
        return todo;
    }

    @Override
    public void deleteById(String id) {
        String sql = "DELETE FROM todos WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
