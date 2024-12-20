package com.k7it.university_course_registration.controller;

import com.k7it.university_course_registration.dto.CourseDto;
import com.k7it.university_course_registration.dto.StudentDto;
import com.k7it.university_course_registration.model.Course;
import com.k7it.university_course_registration.model.Student;
import com.k7it.university_course_registration.repository.CourseRepository;
import com.k7it.university_course_registration.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.security.PrivateKey;
import java.util.List;

/**
 * This Classs is Responisible for  professor APIs */
@RestController
@RequestMapping("professors")
public class ProfessorController {

    @Autowired
    private CourseRepository courseRepository;


        @Autowired
        private ProfessorService professorService;

    /**
     * This API will get courese
     * @param professorId
     * @return
     */
    @GetMapping("{professorId}/courses")
        public ResponseEntity<List<CourseDto>> getCourses(@PathVariable Long professorId) {
            List<CourseDto> courses = professorService.viewProfessorCourses(professorId);
            return ResponseEntity.ok(courses);
        }

    /**
     * This API will update Course
     * @param professorId
     * @param courseId
     * @param courseDto
     * @return
     */
        @PutMapping("{professorId}/courses/{courseId}")
        public ResponseEntity<CourseDto> updateCourse(
                @PathVariable Long professorId,
                @PathVariable Long courseId,
                @RequestBody CourseDto courseDto) {

            CourseDto updatedCourse = professorService.updateCourse(professorId, courseId, courseDto);
            return ResponseEntity.ok(updatedCourse);
        }

    /**
     * This API will view EnrolledStudent
     * @param professorId
     * @param courseId
     * @return
     * @throws AccessDeniedException
     */

    @GetMapping("/{professorId}/courses/{courseId}/students")
    public List<StudentDto> viewEnrolledStudents(@PathVariable Long professorId, @PathVariable Long courseId) throws AccessDeniedException {
       Course course  =courseRepository.getById(courseId);
        if((course.getProfessor().getId()).equals(professorId)) {
            throw new AccessDeniedException("profferson not belongs to this course");
        }
        return professorService.getEnrolledStudents(courseId);
    }
    }


