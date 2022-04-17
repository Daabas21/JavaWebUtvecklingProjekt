package com.example.javawebutvecklingprojekt.controllers;

import com.example.javawebutvecklingprojekt.entities.TodoUser;
import com.example.javawebutvecklingprojekt.repositories.TodoUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usercontroller")
public class TodoUserController {

    @Autowired
    TodoUserRepository todoUserRepository;

    @GetMapping
    public List<TodoUser> todoUsers(){
        return todoUserRepository.findAll();
    }
}
