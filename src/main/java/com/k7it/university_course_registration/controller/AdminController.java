package com.k7it.university_course_registration.controller;


import com.k7it.university_course_registration.dto.CourseDto;
import com.k7it.university_course_registration.dto.CourseDtoFroAddingCouse;
import com.k7it.university_course_registration.dto.StudentDtoToUpdate;
import com.k7it.university_course_registration.model.Complaints;
import com.k7it.university_course_registration.model.Student;
import com.k7it.university_course_registration.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody StudentDtoToUpdate updatedStudent) {
        Student student = adminService.updateStudent(id, updatedStudent);
        return ResponseEntity.ok(student);
    }

    // Endpoint to update student grades
    @PutMapping("students/{id}/grades")
    public ResponseEntity<Student> updateStudentGrades(@PathVariable Long id, @RequestBody int updatedCredits) {
        Student student = adminService.updateStudentGrades(id, updatedCredits);
        return ResponseEntity.ok(student);
    }


    @PutMapping("courses/{courseId}/assignProfessor")
    public ResponseEntity<String> assignProfessorToCourse(
            @PathVariable Long courseId,
            @RequestBody Long professorId
    ) {
        adminService.assignProfessor(courseId, professorId);
        return ResponseEntity.ok("Professor assigned to course successfully.");
    }


    // View all complaints
    @GetMapping("complaints")
    public ResponseEntity<List<Complaints>> getAllComplaints() {
        List<Complaints> complaints = adminService.getAllComplaints();
        return ResponseEntity.ok(complaints);
    }

    // Filter complaints by status
    @GetMapping("complaints/status/{status}")
    public ResponseEntity<List<Complaints>> filterComplaintsByStatus(@PathVariable Complaints.ComplaintStatus status) {
        List<Complaints> complaints = adminService.filterComplaintsByStatus(status);
        return ResponseEntity.ok(complaints);
    }

    // Filter complaints by date
    @GetMapping("complaints/date/{date}")
    public ResponseEntity<List<Complaints>> filterComplaintsByDate(@PathVariable String date) {
        LocalDateTime dateTime;
        try {
            // Update the pattern to match the nanosecond precision
            dateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null); // Return 400 Bad Request if parsing fails
        }
        List<Complaints> complaints = adminService.filterComplaintsByDate(dateTime);
        return ResponseEntity.ok(complaints);
    }

    // Update complaint status
    @PutMapping("complaints/{id}/status")
    public ResponseEntity<Complaints> updateComplaintStatus(@PathVariable Long id, @RequestBody Complaints.ComplaintStatus status) {
        Complaints updatedComplaint = adminService.updateComplaintStatus(id, status);
        return ResponseEntity.ok(updatedComplaint);
    }
}



