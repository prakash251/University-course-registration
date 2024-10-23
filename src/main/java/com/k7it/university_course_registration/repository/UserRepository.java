package com.k7it.university_course_registration.repository;

import com.k7it.university_course_registration.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<Users,Long> {
}
