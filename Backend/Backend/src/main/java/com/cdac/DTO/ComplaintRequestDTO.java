package com.cdac.DTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class ComplaintRequestDTO {

		
	    private String title;
	    private String description;
	    private String category;
	    private String city;

	    private Double locationLat;
	    private Double locationLong;

	    private Long reporterId;  // ID of the reporter (User)
	}


