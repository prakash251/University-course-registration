package com.k7it.university_course_registration.repository;


import com.k7it.university_course_registration.model.CourseStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseStatusRepository extends JpaRepository<CourseStatus,Long> {
   Optional<List< CourseStatus>> findByStudentId(Long studentId);
}
