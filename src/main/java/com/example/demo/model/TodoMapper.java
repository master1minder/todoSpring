package com.example.demo.model;

import com.example.demo.model.Todo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class TodoMapper implements RowMapper<Todo> {

    public Todo mapRow(ResultSet rs, int rowNum) throws SQLException {
        Todo todo = new Todo();
        todo.setId(rs.getInt("id"));
        todo.setName(rs.getString("name"));
        return todo;
    }
}