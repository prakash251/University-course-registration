package com.k7it.university_course_registration.service;

import com.k7it.university_course_registration.repository.CourseRepository;
import com.k7it.university_course_registration.repository.StudentRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private CourseRepository courseRepository;

}