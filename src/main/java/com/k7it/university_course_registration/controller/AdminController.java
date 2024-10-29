
package com.k7it.university_course_registration.controller;


import com.k7it.university_course_registration.dto.CourseDto;
import com.k7it.university_course_registration.dto.CourseDtoFroAddingCouse;
import com.k7it.university_course_registration.dto.StudentDtoToUpdate;
import com.k7it.university_course_registration.model.Complaints;
import com.k7it.university_course_registration.model.CourseStatus;
import com.k7it.university_course_registration.model.Professor;
import com.k7it.university_course_registration.model.Student;
import com.k7it.university_course_registration.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("admin")
/**
 * @apiNote this class responsible  for admin controlloer
 */
public class AdminController {
    @Autowired
    AdminService adminService;

    @PostMapping("add-professor")
    public String addPofessor(@RequestBody Professor professor)
    {
       return adminService.addProfessor(professor);

    }

    @PostMapping("add-student")
    public String addStundt(@RequestBody Student student)
    {
        return adminService.addStundet(student);
    }
    /**
     * This API is add course
     * @param course
     * @return
     */
    @PostMapping("addcourse")
    public ResponseEntity<String> addCourse(@RequestBody CourseDtoFroAddingCouse course) {

        return adminService.addCourse(course);
    }


    /**
     * This ApI will get All Courses
     * @return
     */
    @GetMapping("view-all-courses")
    public ResponseEntity<List<CourseDto>> getAllCourses() {

        return adminService.getAllCourses();
    }


    /**
     * This API will remove course
     * @param courseId
     * @return
     */
    @DeleteMapping("delete-course/{courseId}")
    public ResponseEntity<String> removecourse(@PathVariable long courseId) {

        return adminService.removeCourse(courseId);
    }


    /**
     *This ApI will get all students
     * @return
     */
    @GetMapping("students")
    public List<Student> getAllStudents() {
        return adminService.getAllStudents();
    }

    /**
     * This API will get student by Id
     * @param id
     * @return
     */
    @GetMapping("students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student student = adminService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    /**
     * This API will update student
     * @param id
     * @param updatedStudent
     * @return
     */
    @PutMapping("students/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody StudentDtoToUpdate updatedStudent) {
        Student student = adminService.updateStudent(id, updatedStudent);
        return ResponseEntity.ok(student);
    }

    /**
     * this API will upodate student grades
     * @param id
     * @param updatedCredits
     * @return
     */
    @PutMapping("students/{id}/grades")
    public ResponseEntity<Student> updateStudentGrades(@PathVariable Long id, @RequestBody int updatedCredits) {
        Student student = adminService.updateStudentGrades(id, updatedCredits);
        return ResponseEntity.ok(student);
    }

    /**
     *This API will Assign professor to course
     * @param courseId
     * @param professorId
     * @return
     */
    @PutMapping("courses/courseid/{courseId}/assignProfessor")
    public ResponseEntity<String> assignProfessorToCourse(
            @PathVariable Long courseId,
            @RequestBody Long professorId  ) {
        adminService.assignProfessor(courseId, professorId);
        return ResponseEntity.ok("Professor assigned to course successfully.");
    }

    /**
     * this APi will get all complaints
     * @return
     */
    @GetMapping("complaints")
    public ResponseEntity<List<Complaints>> getAllComplaints() {
        List<Complaints> complaints = adminService.getAllComplaints();
        return ResponseEntity.ok(complaints);
    }

    /**
     * This API will filter complaints by status
     * @param status
     * @return
     */
    @GetMapping("complaints/status/{status}")
    public ResponseEntity<List<Complaints>> filterComplaintsByStatus(@PathVariable Complaints.ComplaintStatus status) {
        List<Complaints> complaints = adminService.filterComplaintsByStatus(status);
        return ResponseEntity.ok(complaints);
    }

    /**
     *This  API will filter complaints by date
     * @param date
     * @return
     */

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

    /**
     *  This API will update the status on Complaints
     * @param id
     * @param status
     * @return
     */

    @PutMapping("complaints/{id}/status")
    public ResponseEntity<Complaints> updateComplaintStatus(@PathVariable Long id, @RequestBody Complaints.ComplaintStatus status) {
        Complaints updatedComplaint = adminService.updateComplaintStatus(id, status);
        return ResponseEntity.ok(updatedComplaint);
    }

    @GetMapping("view-all-courses-status")
    public ResponseEntity<List<CourseStatus>> viewAllCourseStatus()
    {

        return adminService.viewAllCoursesStatus();

    }
}



