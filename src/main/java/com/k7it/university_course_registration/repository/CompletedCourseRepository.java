package com.k7it.university_course_registration.repository;

import com.k7it.university_course_registration.model.CompletedCourses;
import com.k7it.university_course_registration.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompletedCourseRepository extends JpaRepository<CompletedCourses,Long> {
    // List<CompletedCourses> findByStudent(Student student);
    List<CompletedCourses> findByStudent(Student student);

}

