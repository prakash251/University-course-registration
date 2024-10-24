package com.k7it.university_course_registration.controller;


import com.k7it.university_course_registration.dto.CourseDto;
import com.k7it.university_course_registration.model.Course;
import com.k7it.university_course_registration.service.CourseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController
{
        // Get all courses
        @GetMapping("courses")
        public List<CourseDto> getAllCourses() {
            return CourseService.getAllCourses();
        }
    }



