package com.cdac.DTO;

import com.cdac.entities.ComplaintStatus;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ComplaintStatusUpdateDTO {
	  private ComplaintStatus status;
	  private Long updatedBy;
}
