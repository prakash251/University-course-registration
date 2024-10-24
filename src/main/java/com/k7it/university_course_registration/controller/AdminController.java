package com.k7it.university_course_registration.controller;


import com.k7it.university_course_registration.dto.CourseDto;
import com.k7it.university_course_registration.dto.CourseDtoFroAddingCouse;
import com.k7it.university_course_registration.model.Student;
import com.k7it.university_course_registration.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    // Get all courses
    @GetMapping("courses")
    public ResponseEntity<List<CourseDto>> getAllCourses() {
        return adminService.getAllCourses();
    }


    //add courses
    @PostMapping("addcourse")
    public ResponseEntity<String> addCourse(@RequestBody CourseDtoFroAddingCouse course) {

        return adminService.addCourse(course);
    }

    @DeleteMapping("delete-course/{courseId}")
    public ResponseEntity<String> removecourse(@PathVariable long courseId) {

        return adminService.removeCourse(courseId);
    }


    // Endpoint to get all students
    @GetMapping("students")
    public List<Student> getAllStudents() {
        return adminService.getAllStudents();
    }

    // Endpoint to get a student by ID
    @GetMapping("students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student student = adminService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    // Endpoint to update student details
    @PutMapping("students/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent) {
        Student student = adminService.updateStudent(id, updatedStudent);
        return ResponseEntity.ok(student);
    }

    // Endpoint to update student grades
    @PutMapping("students/{id}/grades")
    public ResponseEntity<Student> updateStudentGrades(@PathVariable Long id, @RequestBody int updatedCredits) {
        Student student = adminService.updateStudentGrades(id, updatedCredits);
        return ResponseEntity.ok(student);
    }
}



