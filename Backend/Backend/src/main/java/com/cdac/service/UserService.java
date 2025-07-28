package com.cdac.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cdac.DTO.ApiResponse;
import com.cdac.DTO.SignInDTO;
import com.cdac.DTO.SignInResponseDTO;
import com.cdac.DTO.UserDTO;
import com.cdac.DTO.VolunteerDTO;
import com.cdac.custom_exceptions.ApiException;
import com.cdac.custom_exceptions.AuthenticationException;
import com.cdac.custom_exceptions.ResourceNotFoundException;
import com.cdac.dao.UserDao;
import com.cdac.entities.User;
import com.cdac.entities.UserRole;
import com.cdac.security.JwtUtils;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public  class UserService {
	
     private final UserDao userDao;
	
	private final ModelMapper modelMapper;
	
	
    private final PasswordEncoder passwordEncoder;

    
    private final JwtUtils jwtUtils;
    
    private final AuthenticationManager authenticationManager;

	
	public String addUserDetails(UserDTO adddto) {
		
		  if(userDao.existsByEmail(adddto.getEmail())) {
					throw new ApiException("Duplicate email id");
				}
			
				User user = modelMapper.map(adddto, User.class);

			    // Encode the password here
			    user.setPassword(passwordEncoder.encode(adddto.getPassword()));

			    // Save user
			    userDao.save(user);

			    if (adddto.getRole() == UserRole.ROLE_MANAGER ) {
			        return"Manager registered successfully.";
			    } else if(adddto.getRole() == UserRole.ROLE_REPORTER) {
			        return "Reporter registered successfully.";
			    }
			    else {
			    	return "Volunteer registered successfully.";
	}
			    }

	
	public SignInResponseDTO authenticateUser(SignInDTO dto) {
		 User entity = userDao.findByEmail(dto.getEmail())
			        .orElseThrow(() -> new AuthenticationException("Invalid email"));

			    if (!passwordEncoder.matches(dto.getPassword(), entity.getPassword())) {
			        throw new AuthenticationException("Invalid password");
			    }
			    
			    Authentication authentication = authenticationManager.authenticate(
			            new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword())
			        );

			        // Step 2: Generate JWT using authenticated user
			        String token = jwtUtils.generateJwtToken(authentication);

			        // Step 3: Extract user details from Authentication
			        User userDetails = (User) authentication.getPrincipal();

			        // Step 4: Prepare response DTO
			        SignInResponseDTO response = new SignInResponseDTO();
			        response.setEmail(userDetails.getEmail()); // assuming getEmail() exists
			        response.setRole(userDetails.getRole());   // assuming getRole() exists
			        
			        response.setToken(token);
			        
			    return response;
		
	}

	
	
	public ApiResponse updateUser(Long userID, UserDTO dto) {
		User userEntity = userDao.findById(userID)
				.orElseThrow(() -> 
				new ResourceNotFoundException("Invalid user id !!!!!"));
		
		
		modelMapper.map(dto, userEntity);
		userDao.save(userEntity);
		return new ApiResponse("User details updated successfully");

		
	}

	
	public ApiResponse deleteUser(Long userID) {
		User userEntity = userDao.findById(userID)
				.orElseThrow(() -> 
				new ResourceNotFoundException("Invalid user id !!!!!"));
				userDao.delete(userEntity);
				
				 return new ApiResponse("User deleted!!");
				
	}
	
	 public List<VolunteerDTO> getAllVolunteers() {
	        List<User> users = userDao.findByRole(UserRole.ROLE_VOLUNTEER);

	        return users.stream()
	                .map(user -> modelMapper.map(user, VolunteerDTO.class))
	                .collect(Collectors.toList());
	    }


	public String promoteToSupervisor(Long userId) {
		User user = userDao.findByIdAndRole(userId,UserRole.ROLE_VOLUNTEER)
	            .orElseThrow(() -> new RuntimeException("Volunteer not found"));

	        user.setRole(UserRole.ROLE_SUPERVISOR);
	        userDao.save(user);
	        return "User promoted to supervisor successfully.";
	}


	public UserDTO getUserById(Long id) {
		User user = userDao.findById(id)
	            .orElseThrow(() -> new RuntimeException("User not found"));
	        return modelMapper.map(user, UserDTO.class);
	}


	public List<UserDTO> getTeamUnderSupervisor(Long supervisorId) {
		 List<User> users = userDao.findBySupervisorIdAndRole(supervisorId, UserRole.ROLE_VOLUNTEER);
		    return users.stream()
		                .map(user -> modelMapper.map(user, UserDTO.class))
		                .collect(Collectors.toList());
	}
	
	public String assignVolunteerToSupervisor(Long supervisorId, Long volunteerId) {
	    User supervisor = userDao.findById(supervisorId)
	        .orElseThrow(() -> new RuntimeException("Supervisor not found"));

	    if (!supervisor.getRole().equals(UserRole.ROLE_SUPERVISOR)) {
	        throw new RuntimeException("User is not a supervisor");
	    }

	    User volunteer = userDao.findById(volunteerId)
	        .orElseThrow(() -> new RuntimeException("Volunteer not found"));

	    if (!volunteer.getRole().equals(UserRole.ROLE_VOLUNTEER)) {
	        throw new RuntimeException("User is not a volunteer");
	    }

	    volunteer.setSupervisor(supervisor);
	    userDao.save(volunteer);

	    return "Volunteer assigned to Supervisor successfully.";
	}


}
