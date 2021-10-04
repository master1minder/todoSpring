package com.example.demo.repo;

import com.example.demo.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TodoRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int createTodo(String id,String name) {
        return jdbcTemplate.update("INSERT INTO \"TODO\" (\"NAME\",\"ID\") VALUES(?,?)", name, id);
    }

    public int updateTodo(Todo todo) {
        return jdbcTemplate.update("UPDATE  \"TODO\" SET \"NAME\" = ? WHERE \"ID\" = ?", todo.getName(), todo.getId());
    }

    public int deleteTodo(Integer id) {
        return jdbcTemplate.update("DELETE FROM \"TODO\" WHERE \"ID\" = ?", id);
    }

    public Todo getTodo(Integer id) {
        return jdbcTemplate.queryForObject("SELECT * FROM \"TODO\" WHERE \"ID\"=?", new TodoMapper(), id);
    }

    public List<Todo> getTodo() {
        return jdbcTemplate.query("SELECT * FROM \"TODO\"", new TodoMapper());
    }
}