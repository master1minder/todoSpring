package com.example.demo.controller;

import com.example.demo.model.Todo;
import com.example.demo.repo.TodoRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/")
public class TodoController {


    @Autowired
    private TodoRepository todo;

//    @RequestMapping(value = "/create", method = RequestMethod.PUT, consumes = "text/plain")
//    public int createPerson(@RequestBody String param) {
//        String name = null;
//        try {
//            JSONObject json = new JSONObject(param);
//            name = json.getString("name");
//        } catch (JSONException e) {
//            e.getLocalizedMessage();
//            return 0;
//        }
//        return todo.createTodo(name);
//    }


//    @RequestMapping(value = "update", method = RequestMethod.POST, consumes = "text/plain")
//    public int updatePerson(@RequestBody String param) {
//        Todo t = new Todo();
//        try {
//            JSONObject json = new JSONObject(param);
//            t.setId(json.getInt("id"));
//            t.setName(json.getString("name"));
//        } catch (JSONException e) {
//            e.getLocalizedMessage();
//            return 0;
//        }
//        return todo.updateTodo(t);
//    }


    //?name=параметр
    @RequestMapping(value = "/create", method = RequestMethod.PUT, consumes = "text/plain")
    public int createPerson(@RequestBody String param) {
        String name = null;
        try {
            JSONObject json = new JSONObject(param);
            name = json.getString("name");
        } catch (JSONException e) {
            e.getLocalizedMessage();
            return 0;
        }
        return todo.createTodo(name);
    }
    //?name=имя
    @RequestMapping(value = "/update", method = RequestMethod.POST, consumes = "text/plain")
    public int updatePerson(@RequestBody String param) {
        Todo t = new Todo();
        try {
            JSONObject json = new JSONObject(param);
            t.setId(json.getInt("id"));
            t.setName(json.getString("name"));
        } catch (JSONException e) {
            e.getLocalizedMessage();
            return 0;
        }
        return todo.updateTodo(t);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public int deletePerson(@PathVariable Integer id) {
        return todo.deleteTodo(id);
    }
    //http://localhost:8080/gettodo/?id=1
    @RequestMapping(value = "/gettodo", method = RequestMethod.GET)
    public Todo getPerson(@RequestParam("id") Integer id) {
        return todo.getTodo(id);
    }
    //http://localhost:8080/gettodos
    @RequestMapping(value = "/gettodos", method = RequestMethod.GET)
    public List<Todo> getPersons() {
        return todo.getTodo();
    }
}

