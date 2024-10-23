package com.k7it.university_course_registration.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
public class Complaints{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Student student;

    private String description;

    @Enumerated(EnumType.STRING)
    private ComplaintStatus status;

    private LocalDateTime createdDate;
    public Complaints() {
        this.createdDate = LocalDateTime.now();
        this.status = ComplaintStatus.PENDING; // Default status
    }
    public enum ComplaintStatus {
        PENDING,
        RESOLVED
    }
}

