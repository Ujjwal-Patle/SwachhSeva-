package com.cdac.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.DTO.VolunteerDTO;
import com.cdac.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/volunteers")
@AllArgsConstructor
public class VolunteerController {
	
	@Autowired
	private UserService userService;
	
	@PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_SUPERVISOR')")
	@GetMapping
	public ResponseEntity<?> getAllVolunteers() {
	    List<VolunteerDTO> volunteers = userService.getAllVolunteers();
	    return ResponseEntity.ok(volunteers);
	}

}
