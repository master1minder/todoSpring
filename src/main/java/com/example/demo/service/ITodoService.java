package com.example.demo.service;

import com.example.demo.model.Todo;

import java.util.List;

public interface ITodoService {
    public List<Todo> findAll();
    public Todo save(Todo todo);}
