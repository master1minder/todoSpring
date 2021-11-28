package com.example.demo.controller;

import com.example.demo.model.Todo;
import com.example.demo.repo.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final int ROW_PER_PAGE = 5;

    @Autowired
    private TodoService todoService;


    @RequestMapping("/")
    public String index(Model model) {
        return "index";
    }

    @PostMapping("/")
    public String createTodo(@RequestParam Integer ID,@RequestParam String NAME, Map<String, Object> model) {
        Todo todo=new Todo(ID,NAME);
        todoService.createTodo(NAME,ID);
        return "redirect:/";
    }




    @RequestMapping(value = "/show",method = RequestMethod.GET)
    public String showTodo(Model model) {
            List<Todo> todos =todoService.findByAll();
            model.addAttribute("task",todos);
        return "showTodo";
    }

//    @RequestMapping(value = "/update",method = RequestMethod.PUT)
//    public String updateTodo(@RequestParam Integer ID,@RequestParam String NAME, Map<String, Object> model) {
//        Todo todo=new Todo(ID,NAME);
//        todoRepository.updateTodo(todo);
//        return "redirect:showTodo";
//    }



    @GetMapping(value = {"/update"})

    public String showAddUser(Model model) {
        Todo todo = new Todo();
        model.addAttribute("add", true);
        model.addAttribute("todo", todo);
        return "update";
    }



    @PostMapping(value = "/update")
    public String addUser(Model model,
                          @ModelAttribute("todo") Todo todo) {
        try {
            Todo newTodo = todoService.save(todo);
            return "redirect:/show/" + String.valueOf(newTodo.getId());
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            logger.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("add", true);
            return "update";

        }

    }



    @GetMapping(value = {"/update/{Id}"})

    public String showEditUser(Model model, @PathVariable int Id) {
        Todo todo = null;
        try {
            todo = todoService.findById(Id);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("add", false);
        model.addAttribute("todo", todo);
        return "update";
    }

    @PostMapping(value = {"/update/{Id}"})
    public String updateUser(Model model,
                             @PathVariable int Id,
                             @ModelAttribute("todo") Todo todo) {
        try {
            todo.setId(Id);
            todoService.update(todo);
            return "redirect:/show/" ;
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            logger.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("add", false);
            return "update";
        }

    }



    @GetMapping(value = {"/delete/{Id}/"})
    public String showDeleteUser(
            Model model, @PathVariable int Id) {
        Todo todo = null;
        try {
            todo = todoService.findById(Id);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("allowDelete", true);
        model.addAttribute("todo", todo);
        return "delete";
    }



    @PostMapping(value = {"/delete/{todoId}/"})
    public String deleteUserById(
            Model model, @PathVariable int todoId) {
        try {
            todoService.deleteById(todoId);
            return "redirect:/show";
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            logger.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            return "delete";
        }
    }










//    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
//    public int deleteTodo(@PathVariable Integer id) {
//        return todoService.deleteById(id);
//    }
}
