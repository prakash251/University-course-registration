package com.k7it.university_course_registration.dto;

import lombok.Data;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Data
public class FinalProgessDto {

    private int Semister;
    private Long courseId;
    private String courseCode;
    private String title;
    private int credits;
    private String grade;
    private String cgp;
    private String sgp;

    public FinalProgessDto() {
    }

    public FinalProgessDto( int Semister ,Long courseId, String courseCode, String title, int credits, String grade, String cgp, String sgp) {
        this.Semister=Semister;
        this.courseId = courseId;
        this.courseCode = courseCode;
        this.title = title;
        this.credits = credits;
        this.grade = grade;
        this.cgp = cgp;
        this.sgp = sgp;
    }
}

