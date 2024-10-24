package com.k7it.university_course_registration.repository;

import com.k7it.university_course_registration.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface UserRepository  extends JpaRepository<Users,Long> {
}
