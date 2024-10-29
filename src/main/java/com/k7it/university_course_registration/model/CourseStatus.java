package com.k7it.university_course_registration.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;
    private String courseCode;
    private String courseTitle;
    private int credits;
    private String prerequisites;
    private String timings;
    private int semister;
    private Long professorId;
    private String professorName;
    private Long studentId;
    private String studentName;
    private String status;
    }