package com.k7it.university_course_registration.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;

    @OneToMany
    private List<Course> courses;  // A professor teaches many courses
    public Professor() {
    }

    public Professor(Long id, String name, String email, String password, List<Course> courses) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.courses = courses;
    }
}
