package com.example.javawebutvecklingprojekt.controllers;

import com.example.javawebutvecklingprojekt.entities.Todo;
import com.example.javawebutvecklingprojekt.services.TodoService;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
@AnonymousAllowed
public class TodoController {

    @Autowired
    TodoService todoService;

    @GetMapping
    public List<Todo> getTodoList(@RequestParam(required = false) String name){
        return todoService.findAll(name);
    }

    @GetMapping("/{id}")
    public Todo getTodoById(@PathVariable("id") int id){
        return todoService.findTodoById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTodoById(@PathVariable("id") int id){
        todoService.deleteTodoById(id);
    }

    @PostMapping
    public Todo createNewTodo(@RequestBody Todo todo){
        return  todoService.createNewTodo(todo);
    }

    @PutMapping("/{id}")
    public Todo updateTodoById(@PathVariable("id") int id, @RequestBody Todo newTodo){
        return todoService.updateTodoById(id, newTodo);
    }
}
