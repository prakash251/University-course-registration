package com.k7it.university_course_registration.dto;

import com.sun.jdi.event.StepEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {
        private Long id;
        private String courseCode;
        private String title;
        private int credits;
        private String prerequisites;
        private String timings;
        private int semister;
        private Long professorId;
        private String professorName;


}


