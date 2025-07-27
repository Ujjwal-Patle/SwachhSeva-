package com.cdac.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cdac.DTO.ComplaintAssignmentDTO;
import com.cdac.DTO.ComplaintRequestDTO;
import com.cdac.DTO.ComplaintStatusUpdateDTO;
import com.cdac.entities.Complaint;
import com.cdac.service.ComplaintService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/complaint")
@AllArgsConstructor
public class ComplaintController {
	
	@Autowired
	private ComplaintService complaintService;
	
   @PreAuthorize("hasRole('ROLE_REPORTER')")
	 @PostMapping (consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	    public ResponseEntity<?> submitComplaint(
	            @RequestPart("data") ComplaintRequestDTO complaintRequest ,
	            @RequestPart("image") MultipartFile image,
	            HttpServletRequest request
	    ) {
		 System.out.println("Received Content-Type: " + request.getContentType());
		    System.out.println("Complaint title: " + complaintRequest.getTitle());
		    System.out.println("Image original name: " + image.getOriginalFilename());

	        try {
	            Complaint savedComplaint = complaintService.createComplaint(complaintRequest, image);
	            return ResponseEntity.status(HttpStatus.CREATED).body(savedComplaint);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
	        }
	    }
	 
	 //for manager and supervisor
	 @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_SUPERVISOR')")
	 @GetMapping
	    public ResponseEntity<?> getAllComplaints() {
	        List<Complaint> complaints = complaintService.getAllComplaints();
	        return ResponseEntity.ok(complaints);
	    }
	 
	 //for repoter to see all complaint file by him
	 @GetMapping("/{reporterId}")
	 public ResponseEntity<?> getComplaintsByReporter(@PathVariable Long reporterId) {
	     List<Complaint> complaints = complaintService.getComplaintsByReporterId(reporterId);
	     return ResponseEntity.ok(complaints);
	 }
	
	 //for assign complaint to the supervisor
	@PreAuthorize("hasRole('ROLE_MANAGER')")
	 @PutMapping("/{id}/assign")
	 public ResponseEntity<?> assignComplaintToUser(
	         @PathVariable Long id,
	         @RequestBody ComplaintAssignmentDTO dto
	 ) {
	     try {
	         Complaint updatedComplaint = complaintService.assignComplaint(id, dto.getAssignedToUserId());
	         return ResponseEntity.ok(updatedComplaint);
	     } catch (Exception e) {
	         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Assignment failed: " + e.getMessage());
	     }
	 }
	 
	//for updatting status on coplaint only manager ans supervisor can do
	@PreAuthorize("hasAnyRole('ROLE_SUPERVISOR', 'ROLE_MANAGER')")
	@PutMapping("/{id}/status")
	public ResponseEntity<?> updateComplaintStatus(
	        @PathVariable Long id,
	        @RequestBody ComplaintStatusUpdateDTO statusDTO
	) {
	    try {
	        Complaint updatedComplaint = complaintService.updateStatus(id, statusDTO.getStatus());
	        return ResponseEntity.ok(updatedComplaint);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Status update failed: " + e.getMessage());
	    }
	}

	 //for deleteing the complaint
	@PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_SUPERVISOR')") // restrict if needed
	 @DeleteMapping("/{id}")
	 public ResponseEntity<?> deleteComplaint(@PathVariable Long id) {
	     try {
	         complaintService.deleteComplaintById(id);
	         return ResponseEntity.ok("Complaint deleted successfully.");
	     } catch (Exception e) {
	         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + e.getMessage());
	     }
	 }
}
