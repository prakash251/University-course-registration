package com.k7it.university_course_registration.repository;

import com.k7it.university_course_registration.model.Complaints;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComplaintsRepository extends JpaRepository<Complaints,Long> {
    List<Complaints> findByStudentId(Long studentId);
}
