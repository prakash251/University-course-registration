

package com.k7it.university_course_registration.controller;
import com.k7it.university_course_registration.dto.ComplaintsDto;
import com.k7it.university_course_registration.model.Complaints;
import com.k7it.university_course_registration.service.ComplaintsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("complaints")
public class ComplaintsController {

    @Autowired
    private ComplaintsService complaintService;

    // Submit a complaint
    @PostMapping("{studentId}/submit")
    public ResponseEntity<Complaints> submitComplaint(@PathVariable Long studentId, @RequestBody String description) {
        Complaints complaint = complaintService.submitComplaint(studentId, description);
        return ResponseEntity.ok(complaint);
    }

    // View all complaints by student
    @GetMapping("{studentId}")
    public ResponseEntity<List<ComplaintsDto>> viewComplaints(@PathVariable Long studentId) {
        List<ComplaintsDto> complaints = complaintService.getAllComplaints(studentId);
        return ResponseEntity.ok(complaints);
    }

    // Admin changes complaint status
    @PutMapping("admin/{complaintId}/status")
    public ResponseEntity<Complaints> changeComplaintStatus(@PathVariable Long complaintId, @RequestBody Complaints.ComplaintStatus status) {
        complaintService.changeComplaintStatus(complaintId, status);
        return ResponseEntity.ok().build();
    }
}
