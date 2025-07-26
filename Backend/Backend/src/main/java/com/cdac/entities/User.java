package com.cdac.entities;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cdac.validation.AgeAbove18;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor


public class User implements UserDetails {

	   

		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		private String name;
		
		@Column(unique = true, length = 20)
	    private String aadhaar;

	    @Column(unique = true, length = 15)
	    private String mobile;

		
	    @Email@Column(unique = true, nullable = false)
	    private String email;

	   
	    private String password;
	    
	    @Column(length = 20)
	    private String status; 

	    @Column(name = "created_at")
	    private LocalDateTime createdAt;

	 
		@Column(name = "user_role")
		private UserRole role;
		
		@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	    private List<Complaint> complaints = new ArrayList<>();
		
		
	    private LocalDate dateOfBirth;

	    public User(String email, String password, UserRole role) {
		super();
		this.email = email;
		this.password = password;
		this.role = role;
	}

		
		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			// TODO Auto-generated method stub
			return List.of(new SimpleGrantedAuthority(this.role.name()));
		}

		@Override
		public String getUsername() {
			// TODO Auto-generated method stub
			return email;
		}

	
	

}
