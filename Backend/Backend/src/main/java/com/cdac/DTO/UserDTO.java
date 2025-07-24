package com.cdac.DTO;

import com.cdac.entities.UserRole;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

	  
		
			protected String name;
		    private String aadhaar;
            private String mobile;
            protected String email;
            @NotBlank
            @Pattern(
                regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[#@$*]).{5,20}$",
                message = "Password must be 5-20 characters long and include uppercase, lowercase, digit, and special character (#@$*)"
            )
            private String password;
            
            private UserRole role;
}
//For SignUp 