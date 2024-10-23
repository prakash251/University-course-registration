package com.k7it.university_course_registration.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private int semester;
    private int credits;
    @OneToMany
    private List<Course> registeredCourses;

    public Student() {

    }

    public Student(Long id, String name, String email, String password, int semester, int credits, List<Course> registeredCourses) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.semester = semester;
        this.credits = credits;
        this.registeredCourses = registeredCourses;
    }

}





