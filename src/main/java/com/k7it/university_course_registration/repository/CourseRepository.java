package com.k7it.university_course_registration.repository;

import com.k7it.university_course_registration.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {

    List<Course> findAllBySemister(int semister);

    List<Course> findByProfessorId(Long professorId);

    Optional<Course> findByIdAndProfessorId(Long courseId, Long professorId);
}
