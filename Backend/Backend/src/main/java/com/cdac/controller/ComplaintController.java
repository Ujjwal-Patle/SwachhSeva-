//package com.cdac.controller;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.cdac.entities.Complaint;
//
//import io.swagger.v3.oas.annotations.parameters.RequestBody;
//import jakarta.validation.Valid;
//import lombok.AllArgsConstructor;
//
//@RestController
//@RequestMapping("/api/complaints")
//@AllArgsConstructor
//public class ComplaintController {
//	
//	private final ComplaintService complaintService;
//	
//	@PostMapping
//    public ResponseEntity<?> createComplaint(@RequestBody @Valid ComplaintRequest dto) {
//        Complaint complaint = complaintService.createComplaint(dto);
//        return ResponseEntity.status(HttpStatus.CREATED).body(complaint);
//    }
//
//}
