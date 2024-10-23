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

@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("courses/{semister}")
    public List<CourseDto> viewCourses(@PathVariable int semister) {
        return studentService.viewCourses(semister);
    }

    @PostMapping("{studentId}/register/{courseId}")
    public ResponseEntity<String> registerCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
        studentService.registerForCourse(studentId, courseId);
        return ResponseEntity.ok("Course registered successfully");
    }

    @GetMapping("mycourse/{sid}")
    public List<CourseDto> viewMyCourses(@PathVariable Long sid) {
        return studentService.viewMyCourses(sid);
    }

//schedules for student
    @GetMapping("schedule/{studentId}")
    public ResponseEntity<List<ScheduleDto>> course_Shedule(@PathVariable long studentId)
    {
        return studentService.schedule_course(studentId);
    }

    @GetMapping("{studentId}/progress")
    public ResponseEntity<List<FinalProgessDto>> viewAcademicProgress(@PathVariable Long studentId) {
        List<FinalProgessDto> progressList = studentService.viewAcademicProgress(studentId);
        return ResponseEntity.ok(progressList);
    }

    @DeleteMapping("{studentId}/drop/{courseId}")
    public ResponseEntity<String> dropCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
        studentService.dropCourse(studentId, courseId);
        return ResponseEntity.ok("Course dropped successfully");
    }



}


