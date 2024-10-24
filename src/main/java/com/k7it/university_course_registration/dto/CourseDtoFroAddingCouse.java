package com.k7it.university_course_registration.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDtoFroAddingCouse {
    private Long id;
    private String courseCode;
    private String title;
    private int credits;
    private String prerequisites;
    private String timings;
    private int semister;
    private Long professorId;
    private List<Long> studentId;
}
