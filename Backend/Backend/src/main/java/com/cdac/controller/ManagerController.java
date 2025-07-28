package com.cdac.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.DTO.AssignVolunteerDTO;
import com.cdac.DTO.ComplaintAssignmentDTO;
import com.cdac.entities.Complaint;
import com.cdac.service.ComplaintService;
import com.cdac.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/manager")
@AllArgsConstructor
public class ManagerController {
	
	
	private final UserService userService;
	
	
	private final ComplaintService complaintService; 
	
	
	 //for assign complaint to the supervisor
		@PreAuthorize("hasRole('ROLE_MANAGER')")
		 @PutMapping("/{id}/assign")
		 public ResponseEntity<?> assignComplaintToSupervisor(
		         @PathVariable Long id,
		         @RequestBody ComplaintAssignmentDTO dto
		 ) {
		     try {
		         Complaint updatedComplaint = complaintService.assignComplaint(id, dto.getAssignedToSupervisorId());
		         return ResponseEntity.ok(updatedComplaint);
		     } catch (Exception e) {
		         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Assignment failed: " + e.getMessage());
		     }
		 }
		 
	//assign volunteer to suprvisor
	@PreAuthorize("hasRole('ROLE_MANAGER')")
	@PostMapping("/assign-volunteer")
	public ResponseEntity<?> assignVolunteer(@RequestBody AssignVolunteerDTO dto) {
	    try {
	        String result = userService.assignVolunteerToSupervisor(dto.getSupervisorId(), dto.getVolunteerId());
	        return ResponseEntity.ok(result);
	    } catch (RuntimeException e) {
	        return ResponseEntity.badRequest().body(e.getMessage());
	    }
	}

}
