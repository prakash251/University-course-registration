package com.k7it.university_course_registration.repository;

import com.k7it.university_course_registration.model.Course;
import com.k7it.university_course_registration.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
}
