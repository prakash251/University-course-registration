package com.k7it.university_course_registration.dto;

import com.k7it.university_course_registration.model.Course;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDtoToUpdate {

    private Long id;
    private String name;
    private String email;
    private String password;
    private int semester;
    private int credits;
    private List<Long> registeredCourses;

}
