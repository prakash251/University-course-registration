package com.k7it.university_course_registration.service;

import com.k7it.university_course_registration.dto.ComplaintsDto;
import com.k7it.university_course_registration.model.Complaints;
import com.k7it.university_course_registration.model.Student;
import com.k7it.university_course_registration.repository.ComplaintsRepository;
import com.k7it.university_course_registration.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComplaintsService {
    @Autowired
    private ComplaintsRepository complaintRepository;

    @Autowired
    StudentRepository studentRepository;

    public Complaints submitComplaint(Long studentId, String description) {
        Complaints complaint = new Complaints();
         studentRepository.findById(studentId);
         Student student=studentRepository.findById(studentId).get();
        complaint.setStudent(student); // Set student entity
        complaint.setDescription(description);
        return complaintRepository.save(complaint);
    }

    public List<ComplaintsDto> getAllComplaints(Long stundentId) {
        List<Complaints> complaints = complaintRepository.findByStudentId(stundentId); // Fetch all complaints

        return complaints.stream().map(complaint -> {
            Long studentId = complaint.getStudent().getId();
            return new ComplaintsDto(
                    complaint.getId(),
                    studentId,
                    complaint.getDescription(),
                    complaint.getStatus().toString(),
                    complaint.getCreatedDate() // Add created date here
            );
        }).collect(Collectors.toList());
    }

}