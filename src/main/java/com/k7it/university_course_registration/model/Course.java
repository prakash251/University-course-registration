package com.k7it.university_course_registration.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String courseCode;
    private String title;
    private int credits;
    private String prerequisites;
    private String timings;
    private int semister;
    @ManyToOne
    private Professor professor;
    @ManyToMany
    private List<Student> students;

    public Course()
    {

    }

    public Course(Long id, String courseCode, String title, int credits, String prerequisites, String timings, Professor professor, List<Student> students , int semister) {
        this.id = id;
        this.courseCode = courseCode;
        this.title = title;
        this.credits = credits;
        this.prerequisites = prerequisites;
        this.timings = timings;
        this.professor = professor;
        this.students = students;
        this.semister=semister;
    }
}
