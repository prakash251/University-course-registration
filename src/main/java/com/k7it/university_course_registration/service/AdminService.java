package com.k7it.university_course_registration.service;

import com.k7it.university_course_registration.dto.CourseDto;
import com.k7it.university_course_registration.dto.CourseDtoFroAddingCouse;
import com.k7it.university_course_registration.dto.StudentDtoToUpdate;
import com.k7it.university_course_registration.model.*;
import com.k7it.university_course_registration.repository.*;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {


    @Autowired
    CourseRepository courseRepository;

    @Autowired
    ProfessorRepository professorRepository;
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ComplaintsRepository complaintsRepository;
    @Autowired
    CourseStatusRepository courseStatusRepository;


    public ResponseEntity<List<CourseDto>> getAllCourses() {
        List<Course> courses = courseRepository.findAll();

        return new ResponseEntity<>(courses.stream().map(course -> {

            CourseDto dto = new CourseDto();


            dto.setId(course.getId());
            dto.setCourseCode(course.getCourseCode());
            dto.setTitle(course.getTitle());
            dto.setCredits(course.getCredits());
            dto.setPrerequisites(course.getPrerequisites());
            dto.setTimings(course.getTimings());
            dto.setSemister(course.getSemister());
            dto.setProfessorId(course.getProfessor().getId());
            dto.setProfessorName(course.getProfessor().getName());
            return dto;
        }).collect(Collectors.toList()), HttpStatus.OK);

    }

    public ResponseEntity<String> addCourse(CourseDtoFroAddingCouse course) {

        Course course1=new Course();

        List<Student> student=studentRepository.findAllById(course.getStudentId());
        Professor professor=professorRepository.findById(course.getProfessorId()).get();


         course1.setCourseCode(course.getCourseCode());
         course1.setTitle(course.getTitle());
         course1.setCredits(course.getCredits());
         course1.setPrerequisites(course.getPrerequisites());
         course1.setSemister(course.getSemister());
         course1.setProfessor(professor);
         course1.setStudents(student);

         courseRepository.save(course1);
        return new ResponseEntity<>("course Added Successfully",HttpStatus.OK);
    }


    public ResponseEntity<String> removeCourse(long courseId) {
        courseRepository.deleteById(courseId);
        return new ResponseEntity<>("Deleted successfully",HttpStatus.OK);
    }

    public List<Student> getAllStudents() {

       return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).get();
    }

    public Student updateStudent(Long id, StudentDtoToUpdate updatedStudent) {

        Student student=studentRepository.findById(id).get();
        student.setName(updatedStudent.getName());
        student.setEmail(updatedStudent.getPassword());
        student.setPassword(updatedStudent.getPassword());
        student.setSemester(updatedStudent.getSemester());
        student.setCredits(updatedStudent.getCredits());

        List<Long> studentIds=updatedStudent.getRegisteredCourses();

        List<Course> courses=courseRepository.findAllById(studentIds);
        student.setRegisteredCourses(courses);

        studentRepository.save(student);

        return student;

    }

    public Student updateStudentGrades(Long id, int updatedCredits) {

       Student student =studentRepository.findById(id).get();
       student.setCredits(updatedCredits);
       studentRepository.save(student);

       return student;

    }

    public void assignProfessor(Long courseId, Long professorId) {
        Course course=courseRepository.findById(courseId).get();
       Professor professor= professorRepository.findById(professorId).get();
       course.setProfessor(professor);
       courseRepository.save(course);

    }

    public List<Complaints> getAllComplaints() {

       return complaintsRepository.findAll();


    }

    public List<Complaints> filterComplaintsByStatus(Complaints.ComplaintStatus status) {
        return complaintsRepository.findByStatus(status);
    }

    public Complaints updateResolutionDetails(Long id, String details) {
        return null;
    }

    public Complaints updateComplaintStatus(Long id, Complaints.ComplaintStatus status) {

         Complaints complaints  =complaintsRepository.findById(id).get();
         complaints.setStatus(status);
         complaintsRepository.save(complaints);

        return  complaints;
    }

    public List<Complaints> filterComplaintsByDate(LocalDateTime date) {

        return complaintsRepository.findByCreatedDate(date);
    }

    public ResponseEntity<List<CourseStatus>> viewAllCoursesStatus() {

        List<CourseStatus> courseStatuses=courseStatusRepository.findAll();
        return  new ResponseEntity<>(courseStatuses,HttpStatus.OK);
    }

    public String addStundet(Student student) {

        studentRepository.save(student);
        return "student added successfully";
    }

    public String addProfessor(Professor professor) {
        professorRepository.save(professor);
        return "professor added Succesfully";
    }
}
