package com.cdac.DTO;

import java.time.LocalDate;

import com.cdac.entities.UserRole;
import com.cdac.validation.AgeAbove18;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
            

            @JsonFormat(pattern = "yyyy-MM-dd") 
            @NotNull
            @AgeAbove18
            private LocalDate dateOfBirth; 
            
            private UserRole role;
}
//For SignUp 