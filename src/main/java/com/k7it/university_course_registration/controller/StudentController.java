package com.k7it.university_course_registration.controller;

import com.k7it.university_course_registration.dto.AcademicProgressDto;
import com.k7it.university_course_registration.dto.CourseDto;
import com.k7it.university_course_registration.dto.FinalProgessDto;
import com.k7it.university_course_registration.dto.ScheduleDto;
import com.k7it.university_course_registration.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This Class Is responsible form student APIS
 */
@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * This API will view courses
     * @param semister
     * @return
     */
    @GetMapping("courses/{semister}")
    public List<CourseDto> viewCourses(@PathVariable int semister) {
        return studentService.viewCourses(semister);
    }

    /**
     * This API will Register Courses
     * @param studentId
     * @param courseId
     * @return
     */
    @PostMapping("{studentId}/register/{courseId}")
    public ResponseEntity<String> registerCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
        studentService.registerForCourse(studentId, courseId);
        return ResponseEntity.ok("Course registered successfully");
    }

    /**
     * This API will View Courses
     * @param sid
     * @return
     */
    @GetMapping("mycourse/{sid}")
    public List<CourseDto> viewMyCourses(@PathVariable Long sid) {
        return studentService.viewMyCourses(sid);
    }

    /**
     * This API Schedule Course
     * @param studentId
     * @return
     */
    @GetMapping("schedule/{studentId}")
    public ResponseEntity<List<ScheduleDto>> course_Schedule(@PathVariable long studentId)
    {
        return studentService.schedule_course(studentId);
    }

    /**
     * This APi View Acadamic Progress
     * @param studentId
     * @return
     */
    @GetMapping("{studentId}/progress")
    public ResponseEntity<List<FinalProgessDto>> viewAcademicProgress(@PathVariable Long studentId) {
        List<FinalProgessDto> progressList = studentService.viewAcademicProgress(studentId);
        return ResponseEntity.ok(progressList);
    }

    /**
     * This Api Drop Course
     * @param studentId
     * @param courseId
     * @return
     */
    @DeleteMapping("{studentId}/drop/{courseId}")
    public ResponseEntity<String> dropCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
        studentService.dropCourse(studentId, courseId);
        return ResponseEntity.ok("Course dropped successfully");
    }


    /**
     * This Api Add course in completed courses
     * @param studntId
     * @param courseId
     * @return
     */
    @PostMapping("{studntId}/addcompletedcourse/{courseId}")
   public ResponseEntity<String> addCompletedCourse(@PathVariable Long studntId,@PathVariable Long courseId)
   {
       return studentService.addcompletedCourse(studntId,courseId);
   }

}


