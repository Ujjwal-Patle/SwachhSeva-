package com.cdac.dao;


import com.cdac.entities.User;
import com.cdac.entities.UserRole;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserDao extends JpaRepository<User, Long> {

    // Find user by email or mobile (for login)
    Optional<User> findByEmail(String email);
    
    Optional<User> findByMobile(String mobile);
    
    // Optional: login using either email or aadhaar
    Optional<User> findByEmailOrAadhaar(String email, String aadhaar);
    
    Boolean existsByEmail(String email);
    
    Optional<User> findByIdAndRole(Long id,UserRole role);
 
	List<User> findByRole(UserRole role);

	List<User> findBySupervisorIdAndRole(Long supervisorId, UserRole roleVolunteer);
}
