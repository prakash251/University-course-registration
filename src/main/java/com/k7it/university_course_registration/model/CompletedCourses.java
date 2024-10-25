package com.k7it.university_course_registration.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class CompletedCourses {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        private Course course;

        @ManyToOne
        private Student student;

        private int semester;
        private int credits;

        private String grade; // Assuming grades are letters like A, B, C, etc.

    public CompletedCourses() {

    }

    public CompletedCourses(Long id, Course course, Student student, int semester,int credits, String grade) {
        this.id = id;
        this.course = course;
        this.student = student;
        this.semester = semester;
        this.credits=credits;
        this.grade = grade;
    }
}
