package com.example.demo.controller;

import com.example.demo.model.Todo;
import com.example.demo.repo.TodoRepository;
import com.example.demo.service.ITodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private ITodoService todoService;

    @Autowired
    private TodoRepository todoRepository;

    @RequestMapping("/")
    public String index(Model model) {

        return "index";
    }
    //http://localhost:8080/?ID=10&NAME=Sport
    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = "text/plain")
    public String createPerson(@RequestParam(name="ID", required=false) String ID, @RequestParam(name="NAME", required=false) String NAME) {
        Todo todo =new Todo();
        todo.setId(Integer.parseInt(ID));
        todo.setName(NAME);

        todoService.save(todo);

        return "User add";
    }




    @RequestMapping(value = "/show",method = RequestMethod.GET)
    public String showCars(Model model) {

//        List<Todo> todos = todoService.findAll();
//
//        Map<String, Object> params = new HashMap<>();
//        params.put("todo", todos);

            List<Todo> todos =todoService.findAll();
            model.addAttribute("task",todos);
        return "showTodo";
    }



}
