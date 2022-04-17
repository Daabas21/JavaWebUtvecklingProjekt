package com.example.javawebutvecklingprojekt.repositories;

import com.example.javawebutvecklingprojekt.entities.TodoUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TodoUserRepository extends JpaRepository<TodoUser, Integer> {

    Optional<TodoUser> findTodoUserByEmail(String email);
    Optional<TodoUser> findTodoUserByName(String name);

}
