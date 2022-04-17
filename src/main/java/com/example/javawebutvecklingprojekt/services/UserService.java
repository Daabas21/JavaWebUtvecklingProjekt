package com.example.javawebutvecklingprojekt.services;

import com.example.javawebutvecklingprojekt.entities.Todo;
import com.example.javawebutvecklingprojekt.entities.TodoUser;
import com.example.javawebutvecklingprojekt.repositories.TodoUserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    TodoUserRepository todoUserRepository;

    public TodoUser findUserById(int id){
        return todoUserRepository.findById(id).orElseThrow();
    }

    public void deleteUserById(int id){
        todoUserRepository.deleteById(id);
    }

    public TodoUser createNewUser(TodoUser todoUser){
        return todoUserRepository.save(todoUser);
    }

    public TodoUser updateUser(int id, TodoUser todoUser){
        TodoUser existingUser = findUserById(id);

        BeanUtils.copyProperties(todoUser , existingUser, "id");
        return createNewUser(todoUser);
    }

}
