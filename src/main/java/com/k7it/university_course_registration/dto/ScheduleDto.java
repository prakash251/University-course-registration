package com.k7it.university_course_registration.dto;

import lombok.Data;

@Data
public class ScheduleDto {

        private String courseTitle;
        private String timings;
        private String professorName;

    public ScheduleDto() {
    }
    public ScheduleDto(String courseTitle, String timings, String professorName) {
        this.courseTitle = courseTitle;
        this.timings = timings;
        this.professorName = professorName;

    }
}


