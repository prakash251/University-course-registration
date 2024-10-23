package com.k7it.university_course_registration.dto;

import lombok.Data;

import java.util.List;

@Data
public class AcademicProgressDto {

    private List<SemesterProgressDto> semesterProgress;

        private double cgpa;

    public AcademicProgressDto() {
    }

    public AcademicProgressDto(List<SemesterProgressDto> semesterProgress, double cgpa) {
            this.semesterProgress = semesterProgress;
            this.cgpa = cgpa;
        }
    }


