package com.k7it.university_course_registration.repository;

import com.k7it.university_course_registration.model.Complaints;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplaintsRepository extends JpaRepository<Complaints,Long> {
    List<Complaints> findByStudentId(Long studentId);
}
