package com.k7it.university_course_registration.repository;

import com.k7it.university_course_registration.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor,Long> {
}
