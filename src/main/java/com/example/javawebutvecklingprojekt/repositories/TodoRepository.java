package com.example.javawebutvecklingprojekt.repositories;

import com.example.javawebutvecklingprojekt.entities.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {
    List<Todo> findByTodoUserName(String name);
}
