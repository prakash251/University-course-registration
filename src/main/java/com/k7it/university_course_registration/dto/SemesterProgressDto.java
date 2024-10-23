package com.k7it.university_course_registration.dto;

import com.k7it.university_course_registration.model.CompletedCourses;
import lombok.Data;

import java.util.List;

@Data
public class SemesterProgressDto {

        private int semester;
        private List<CompletedCourses> completedCourses;
        private double sgpa;

        public SemesterProgressDto(int semester, List<CompletedCourses> completedCourses, double sgpa) {
            this.semester = semester;
            this.completedCourses = completedCourses;
            this.sgpa = sgpa;
        }

        // Getters and Setters
    }

