package com.example.javawebutvecklingprojekt.security;

import com.example.javawebutvecklingprojekt.entities.TodoUser;
import com.example.javawebutvecklingprojekt.repositories.TodoUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    TodoUserRepository todoUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        TodoUser todoUser = todoUserRepository.findTodoUserByEmail(username).orElseThrow();

        return new User(todoUser.getName(), todoUser.getPassword(), List.of());
    }
}
