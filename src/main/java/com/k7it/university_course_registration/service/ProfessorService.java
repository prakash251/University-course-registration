package com.k7it.university_course_registration.service;

import com.k7it.university_course_registration.dto.CourseDto;
import com.k7it.university_course_registration.dto.StudentDto;
import com.k7it.university_course_registration.model.Course;
import com.k7it.university_course_registration.model.Professor;
import com.k7it.university_course_registration.model.Student;
import com.k7it.university_course_registration.repository.CourseRepository;
import com.k7it.university_course_registration.repository.ProfessorRepository;
import com.k7it.university_course_registration.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ClientInfoStatus;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ProfessorService {

    @Autowired
    ProfessorRepository professorRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    StudentRepository studentRepository;

    public List<CourseDto> viewProfessorCourses(Long professorId) {

        List<Course> courses = courseRepository.findByProfessorId(professorId);

        return courses.stream().map(course ->
        {
            CourseDto dto=new CourseDto();
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
        }).collect(Collectors.toList());


    }

    public CourseDto updateCourse(Long professorId, Long courseId, CourseDto courseDto) {
       Course course  =courseRepository.findByIdAndProfessorId(courseId,professorId).orElseThrow(()-> new RuntimeException("course not found"));

     Professor professor=professorRepository.findById(courseDto.getProfessorId()).orElseThrow(()-> new NoSuchElementException("the element with id "+course.getProfessor().getId()+" not avialable"));

     // Update only the allowed fields

        course.setCourseCode(courseDto.getCourseCode());
        course.setTitle(courseDto.getTitle());
        course.setCredits(courseDto.getCredits());
        course.setPrerequisites(courseDto.getPrerequisites());
        course.setTimings(courseDto.getTimings());
        course.setSemister(courseDto.getSemister());
        course.setProfessor(professor);

        courseRepository.save(course);





        return courseDto;
    }

    public List<StudentDto> getEnrolledStudents(Long courseId) {

   Course course  = courseRepository.findById(courseId).orElseThrow(()-> new RuntimeException("course not found"));


              List<Student> studentnts=  course.getStudents();
           return studentnts.stream().map(student->
              {
                  StudentDto dto=new StudentDto();
                  dto.setId(student.getId());
                  dto.setName(student.getName());
                  dto.setEmail(student.getEmail());
                  dto.setPassword(student.getPassword());
                  dto.setSemester(student.getSemester());

                  dto.setCredits(student.getCredits());

                   return  dto;
              }).collect(Collectors.toList());



    }
}
