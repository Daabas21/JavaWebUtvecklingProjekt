package com.example.javawebutvecklingprojekt.entities;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_Id")
    private int id;
    private String place;
    private String todo;
    private String day;
    private String tid;

    @ManyToOne
    @JoinColumn(name = "user_Id")
    private TodoUser todoUser;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Todo todo = (Todo) o;
        return false;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
