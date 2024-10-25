package com.k7it.university_course_registration.service;

import com.k7it.university_course_registration.dto.*;
import com.k7it.university_course_registration.model.CompletedCourses;
import com.k7it.university_course_registration.model.Course;
import com.k7it.university_course_registration.model.Student;
import com.k7it.university_course_registration.repository.CompletedCourseRepository;
import com.k7it.university_course_registration.repository.CourseRepository;
import com.k7it.university_course_registration.repository.StudentRepository;
import jakarta.persistence.ManyToOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CompletedCourseRepository completedCourseRepository;

    public List<CourseDto> viewCourses(int semister) {

        List<Course> courses = courseRepository.findAllBySemister(semister);
        return courses.stream().map(course -> {
            CourseDto dto = new CourseDto();
            dto.setId(course.getId());
            dto.setCourseCode(course.getCourseCode());
            dto.setTitle(course.getTitle());
            dto.setCredits(course.getCredits());
            dto.setPrerequisites(course.getPrerequisites());
            dto.setTimings(course.getTimings());
            dto.setSemister(course.getSemister());
            dto.setProfessorId(course.getProfessor().getId());
            dto.setProfessorName(course.getProfessor().getName());// Fetch only the professor ID
            return dto;
        }).collect(Collectors.toList());
    }

    public ResponseEntity<String> registerForCourse(Long studentId, Long courseId) {

        // Fetch student and course details
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));

        // Check if the student is registering for a course in their current semester
        if (student.getSemester() < course.getSemister()) {
            return ResponseEntity.badRequest().body("Cannot register for a course in a future semester.");
        }

        // Check if adding the course exceeds the credit limit
        int totalCredits = student.getCredits() + course.getCredits();
        if (totalCredits > 20) {
            return ResponseEntity.badRequest().body("Cannot register: total credits exceed the limit of 20.");
        }

        // Register the student for the course
        student.getRegisteredCourses().add(course);
        student.setCredits(totalCredits);
        studentRepository.save(student);

        return ResponseEntity.ok("Successfully registered for the course: " + course.getTitle());
    }

    public List<CourseDto> viewMyCourses(Long sid) {

        Student student = studentRepository.findById(sid).get();

        List<Course> mycourses = student.getRegisteredCourses();
        List<CourseDto> courseDTOs = mycourses.stream().map(course ->
            new CourseDto(
                    course.getId(),
                    course.getCourseCode(),
                    course.getTitle(),
                    course.getCredits(),
                    course.getPrerequisites(),
                    course.getTimings(),
                    course.getSemister(),
                    course.getProfessor().getId(),
                    course.getProfessor().getName())

        ).collect(Collectors.toList());

        return courseDTOs;
    }

    public ResponseEntity<List<ScheduleDto>> schedule_course(long studentId) {

        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("student not found"));
        List<Course> courses = student.getRegisteredCourses();

        List<ScheduleDto> schedulecourse = courses.stream()
                .map(course ->
                        new ScheduleDto(
                                course.getTitle(),
                                course.getTimings(),
                                course.getProfessor().getName())).collect(Collectors.toList());

        return new ResponseEntity<>(schedulecourse, HttpStatus.OK);
    }

    public List<FinalProgessDto> viewAcademicProgress(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        // Get all completed courses for the student
        List<CompletedCourses> completedCourses = completedCourseRepository.findByStudent(student);

        // Separate courses by semester and calculate SGPA
        Map<Integer, List<CompletedCourses>> coursesBySemester = completedCourses.stream()
                .collect(Collectors.groupingBy(CompletedCourses::getSemester));

        List<FinalProgessDto> finalProgressList = new ArrayList<>();

        for (Map.Entry<Integer, List<CompletedCourses>> entry : coursesBySemester.entrySet()) {
            int semester = entry.getKey();
            List<CompletedCourses> coursesInSemester = entry.getValue();
            double sgpa = calculateGPA(coursesInSemester);  // SGPA for the semester

            for (CompletedCourses completedCourse : coursesInSemester) {
                FinalProgessDto dto = new FinalProgessDto(
                        completedCourse.getSemester(),
                        completedCourse.getCourse().getId(),
                        completedCourse.getCourse().getCourseCode(),
                        completedCourse.getCourse().getTitle(),
                        completedCourse.getCourse().getCredits(),
                        completedCourse.getGrade(),
                        String.format("%.2f", calculateGPA(completedCourses)),  // CGPA
                        String.format("%.2f", sgpa)  // SGPA

                );
                finalProgressList.add(dto);
            }
        }

        return finalProgressList;
    }

    private double calculateGPA(List<CompletedCourses> courses) {
        int totalCredits = 0;
        double totalGradePoints = 0.0;

        for (CompletedCourses completedCourse : courses) {
            int credits = completedCourse.getCourse().getCredits();
            double gradePoint = convertGradeToPoint(completedCourse.getGrade()); // Convert letter grade to points (e.g., A = 10)
            totalCredits += credits;
            totalGradePoints += (gradePoint * credits);
        }

        return totalCredits == 0 ? 0.0 : totalGradePoints / totalCredits;
    }

    private double convertGradeToPoint(String grade) {
        switch (grade.toUpperCase()) {
            case "A": return 10.0;
            case "B": return 8.0;
            case "C": return 6.0;
            case "D": return 4.0;
            case "F": return 0.0;
            default: throw new IllegalArgumentException("Unknown grade: " + grade);
        }
    }

    //drop course
    public void dropCourse(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        // Remove the course from the student's registered courses
        if (student.getRegisteredCourses().contains(course)) {
            student.getRegisteredCourses().remove(course);
            student.setCredits(student.getCredits() - course.getCredits()); // Update student credits
            studentRepository.save(student);
        } else {
            throw new RuntimeException("Course not found in student's registered courses");
        }
    }
    public ResponseEntity<String> addcompletedCourse(Long studentID,Long courseId) {

       Course course =courseRepository.findById(courseId).get();
       Student student=studentRepository.findById(studentID).get();
       CompletedCourses completedCourses=new CompletedCourses();

       completedCourses.setCourse(course);
       completedCourses.setStudent(student);
       completedCourses.setSemester(course.getSemister());
       completedCourses.setCredits(course.getCredits()+2);
       completedCourses.setGrade("A");
       completedCourseRepository.save(completedCourses);
        return new  ResponseEntity<>("completed successfully",HttpStatus.OK);
    }
}

