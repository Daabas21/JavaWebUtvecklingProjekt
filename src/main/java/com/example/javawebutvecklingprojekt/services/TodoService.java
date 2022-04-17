package com.example.javawebutvecklingprojekt.services;

import com.example.javawebutvecklingprojekt.entities.Todo;
import com.example.javawebutvecklingprojekt.entities.TodoUser;
import com.example.javawebutvecklingprojekt.repositories.TodoRepository;
import com.example.javawebutvecklingprojekt.repositories.TodoUserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    TodoRepository todoRepository;

    @Autowired
    TodoUserRepository todoUserRepository;

    public List<Todo> findAll(String name) {
        if (name == null) {
            return todoRepository.findAll();
        }else
            return todoRepository.findByTodoUserName(name);
    }

    public Todo findTodoById(int id) {
        return todoRepository.findById(id).orElseThrow();
    }

    public void deleteTodoById(int id) {
        todoRepository.deleteById(id);
    }

    public Todo createNewTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public void createNewTodo(Todo todo, String name){
        TodoUser todoUser = todoUserRepository.findTodoUserByName(name).orElseThrow();

        todo.setTodoUser(todoUser);

        todoRepository.save(todo);
    }

    public Todo updateTodoById(int id, Todo newTodo) {
        Todo existingTodo = findTodoById(id);

        BeanUtils.copyProperties(newTodo, existingTodo, "todoId");
        return todoRepository.save(existingTodo);
    }

}
