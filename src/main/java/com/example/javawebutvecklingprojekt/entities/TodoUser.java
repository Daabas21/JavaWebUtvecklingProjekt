package com.example.javawebutvecklingprojekt.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "Todo_User")
public class TodoUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String password;
    private String image;

    @OneToMany(mappedBy = "todoUser")
    @JsonIgnore
    private Set<Todo> todos;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TodoUser todoUser = (TodoUser) o;
        return false;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
