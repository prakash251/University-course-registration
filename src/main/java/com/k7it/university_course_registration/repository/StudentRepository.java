package com.k7it.university_course_registration.repository;

import com.k7it.university_course_registration.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
