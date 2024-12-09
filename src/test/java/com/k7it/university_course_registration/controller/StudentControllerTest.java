package com.k7it.university_course_registration.controller;

import com.k7it.university_course_registration.dto.CourseDto;
import com.k7it.university_course_registration.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
class StudentControllerTest
{

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Test
    public void testViewCoursesBySemester() throws Exception
    {
        // Mock the service method
        when(studentService.viewCourses(1))
                .thenReturn(Arrays.asList(
                        new CourseDto(1L, "CSE101", "Introduction to Computer Science", 3, "None", "9:00 AM - 10:30 AM", 1, 101L, "Dr. Smith"),
                        new CourseDto(2L, "CSE102", "Data Structures", 4, "CSE101", "11:00 AM - 12:30 PM", 1, 102L, "Dr. Johnson")
                ));

        // Perform GET request and verify response
        mockMvc.perform(get("/student/view-all-courses-by-semister/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].courseCode").value("CSE101"))
                .andExpect(jsonPath("$[0].title").value("Introduction to Computer Science"))
                .andExpect(jsonPath("$[0].credits").value(3))
                .andExpect(jsonPath("$[0].prerequisites").value("None"))
                .andExpect(jsonPath("$[0].timings").value("9:00 AM - 10:30 AM"))
                .andExpect(jsonPath("$[0].semister").value(1))
                .andExpect(jsonPath("$[0].professorId").value(101L))
                .andExpect(jsonPath("$[0].professorName").value("Dr. Smith"))
                .andExpect(jsonPath("$[1].courseCode").value("CSE102"))
                .andExpect(jsonPath("$[1].title").value("Data Structures"))
                .andExpect(jsonPath("$[1].credits").value(4))
                .andExpect(jsonPath("$[1].prerequisites").value("CSE101"))
                .andExpect(jsonPath("$[1].timings").value("11:00 AM - 12:30 PM"))
                .andExpect(jsonPath("$[1].semister").value(1))
                .andExpect(jsonPath("$[1].professorId").value(102L))
                .andExpect(jsonPath("$[1].professorName").value("Dr. Johnson"));

        // Verify that the service method was called
        verify(studentService, times(1)).viewCourses(1);
    }

    @Test
    public void testRegisterCourse() throws Exception {
        when(studentService.registerForCourse(1l, 1l)).thenReturn(ResponseEntity.ok("Course registered successfully"));

        // Perform the POST request and verify the response
        mockMvc.perform(post("/student/studentid/1/register/courseid/1"))
                .andExpect(status().isOk())  // Expecting HTTP 200 status
                .andExpect(content().string("Course registered successfully"));  // Expecting success message

        // Verify that the service method was called with the correct arguments
        verify(studentService, times(1)).registerForCourse(1L, 1L);
    }


}

