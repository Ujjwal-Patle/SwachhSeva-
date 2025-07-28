package com.cdac.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter

public class VolunteerDTO {
	private Long id;
    private String name;
    private String email;
    private String mobile;
    private String role;
}
