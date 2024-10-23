package com.k7it.university_course_registration.repository;

import com.k7it.university_course_registration.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Long> {
}
