package com.cdac.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.DTO.ApiResponse;
import com.cdac.DTO.SignInDTO;
import com.cdac.DTO.SignInResponseDTO;
import com.cdac.DTO.UserDTO;
import com.cdac.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;



@RestController
@RequestMapping("/user")
@AllArgsConstructor

public class UserController {
	
	private final UserService userService;

	

	@PostMapping("/signup")
	@Operation(description = "New user sign up here!!")
	public ResponseEntity<?> addUserDetails(@RequestBody @Valid UserDTO adddto){
		try {
			System.out.println("in add user controller method");
			return ResponseEntity.ok(userService.addUserDetails(adddto));
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
		
	}
	
	@PostMapping("/signin")
	@Operation(description = "User sign in")
	public ResponseEntity<?> userSignIn
	(@RequestBody @Valid SignInDTO dto)
	{
		System.out.println("in user sign in "+dto);
		SignInResponseDTO response = userService.authenticateUser(dto);
	    return ResponseEntity.ok(response);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_SUPERVISOR', 'ROLE_REPORTER', 'ROLE_VOLUNTEER')")
	public ResponseEntity<?> getUserById(@PathVariable Long id) {
	    try {
	        UserDTO dto = userService.getUserById(id);
	        return ResponseEntity.ok(dto);
	    } catch (RuntimeException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    }
	}
	
	
}