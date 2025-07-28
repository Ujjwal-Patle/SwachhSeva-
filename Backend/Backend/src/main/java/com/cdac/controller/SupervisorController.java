package com.cdac.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.DTO.PromotionRequestDTO;
import com.cdac.DTO.UserDTO;
import com.cdac.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/supervisors")
@AllArgsConstructor
public class SupervisorController {
	
	
	private final UserService userService;

	@PreAuthorize("hasRole('ROLE_MANAGER')")
	@PostMapping("/promote")
	public ResponseEntity<?> promoteVolunteer(@RequestBody PromotionRequestDTO dto) {
	    try {
	        String result = userService.promoteToSupervisor(dto.getUserId());
	        return ResponseEntity.ok(result);
	    } catch (RuntimeException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    }
	}
	
	@GetMapping("/{id}/team")
	@PreAuthorize("hasAnyRole('ROLE_SUPERVISOR', 'ROLE_MANAGER')")
	public ResponseEntity<?> getVolunteersUnderSupervisor(@PathVariable Long id) {
	    List<UserDTO> team = userService.getTeamUnderSupervisor(id);
	    return ResponseEntity.ok(team);
	}
	
}
