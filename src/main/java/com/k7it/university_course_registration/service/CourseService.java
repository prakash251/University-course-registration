package com.k7it.university_course_registration.service;

import com.k7it.university_course_registration.dto.CourseDto;
import com.k7it.university_course_registration.model.Course;
import com.k7it.university_course_registration.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class CourseService {

    @Autowired
   private CourseRepository courseRepository;

    public List<CourseDto> getAllCourses() {
        List<Course> courses=courseRepository.findAll();

        courses.stream().map(()->{


            return null;
        }).collect(Collectors.toList());



    }
}
