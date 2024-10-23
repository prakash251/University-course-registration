package com.k7it.university_course_registration.dto;

import com.k7it.university_course_registration.model.Complaints;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ComplaintsDto {

        private Long complaintId;
        private Long studentId;
      //  private Long courseId; // You might want to add this depending on your data structure
        private String description;
        private  String status;
        private LocalDateTime createdDate; // Add this line

        public ComplaintsDto(Long complaintId, Long studentId, String description, String status, LocalDateTime createdDate) {
            this.complaintId = complaintId;
            this.studentId = studentId;
          //  this.courseId = courseId;
            this.description = description;
            this.status = status;
            this.createdDate = createdDate; // Initialize this field
        }
    }

