package com.cdac.service;

import java.util.List;

import com.cdac.DTO.ApiResponse;
import com.cdac.DTO.SignInDTO;
import com.cdac.DTO.SignInResponseDTO;
import com.cdac.DTO.UserDTO;

public interface UserService {

	String addUserDetails(UserDTO adddto);

	SignInResponseDTO authenticateUser(SignInDTO dto);

	

	ApiResponse updateUser(Long userID, UserDTO dto);

	ApiResponse deleteUser( Long userID);

}
