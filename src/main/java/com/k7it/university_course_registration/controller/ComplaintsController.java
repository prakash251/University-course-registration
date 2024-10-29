

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
/**
 * THis Class is Responisble for Complints
 */
public class ComplaintsController {

    @Autowired
    private ComplaintsService complaintService;

    /**
     * This APi is used to submit compliants
     * @param studentId
     * @param description
     * @return
     */
    @PostMapping("studentid/{studentId}/submit")
    public ResponseEntity<Complaints> submitComplaint(@PathVariable Long studentId, @RequestBody String description) {
        Complaints complaint = complaintService.submitComplaint(studentId, description);
        return ResponseEntity.ok(complaint);
    }

    /**
     * This API is Used for view complints
     * @param studentId
     * @return
     */
    @GetMapping("view-complaints-by-studentid/{studentId}")
    public ResponseEntity<List<ComplaintsDto>> viewComplaints(@PathVariable Long studentId) {
        List<ComplaintsDto> complaints = complaintService.getAllComplaints(studentId);
        return ResponseEntity.ok(complaints);
    }



}
