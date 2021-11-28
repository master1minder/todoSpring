package com.example.demo.repo;

import com.example.demo.model.Todo;
import com.example.demo.model.TodoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int createTodo(String name, Integer id) {
        return jdbcTemplate.update("INSERT INTO \"TODO\" (\"NAME\",\"ID\") VALUES(?,?)", name, id);
    }
//
//    public Todo create(Todo todo) {
//        return jdbcTemplate.queryForObject("INSERT INTO \"TODO\" (\"NAME\",\"ID\") VALUES(?,?)", new TodoMapper());
//    }
//
//    public int updateTodo(Todo todo) {
//        return jdbcTemplate.update("UPDATE  \"TODO\" SET \"NAME\" = ? WHERE \"ID\" = ?", todo.getName(), todo.getId());
//    }
//
//    public int deleteTodo(Integer id) {
//        return jdbcTemplate.update("DELETE FROM \"TODO\" WHERE \"ID\" = ?", id);
//    }
//
//    public Todo getTodo(Integer id) {
//        return jdbcTemplate.queryForObject("SELECT * FROM \"TODO\" WHERE \"ID\"=?", new TodoMapper(), id);
//    }
//
//    public List<Todo> getTodo() {
//        return jdbcTemplate.query("SELECT * FROM \"TODO\"", new TodoMapper());
//    }


    public List<Todo> findByAll(){return jdbcTemplate.query("SELECT * FROM \"TODO\"",new TodoMapper());}

    public Todo findById(Integer id){return jdbcTemplate.queryForObject("SELECT * FROM \"TODO\" WHERE \"ID\"=?",new TodoMapper(),id);}

    public Todo save(Todo todo) {return jdbcTemplate.queryForObject("INSERT INTO \"TODO\" (\"NAME\",\"ID\") VALUES(?,?)", new TodoMapper());}

    public int add(String name) {return jdbcTemplate.update("INSERT INTO \"PERSONS\" (\"NAME\") VALUES(?)", name);}

    public int update(Todo todo) {return jdbcTemplate.update("UPDATE  \"TODO\" SET \"NAME\" = ? WHERE \"ID\" = ?", todo.getName(), todo.getId());}

    public int deleteById(Integer id) {
        return jdbcTemplate.update("DELETE FROM \"TODO\" WHERE \"ID\" = ?", id);
    }

}