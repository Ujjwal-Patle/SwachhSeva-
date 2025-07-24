package com.cdac.DTO;

import com.cdac.entities.UserRole;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignInDTO {
	@NotBlank
	private String email;
	@NotBlank
	private String password;
	private UserRole role;
	}
	


