package com.example.demo.service;

import com.example.demo.model.Todo;
import com.example.demo.repo.TodoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class TodoService implements ITodoService{
    @Autowired
    private JdbcTemplate jtm;

    @Override
    public List<Todo> findAll() {

        String sql = "SELECT * FROM \"TODO\"";

        List<Todo> todos = jtm.query(sql, new BeanPropertyRowMapper(Todo.class));

        return todos;
    }

    @Override
    public Todo save(Todo todo) {

        String sql="INSERT INTO \"TODO\" (\"ID\",\"NAME\") VALUES("+todo.getId()+","+todo.getName()+")";
        todo= (Todo) jtm.query(sql,new TodoMapper());
        return todo;
    }
}
