package com.cdac.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cdac.DTO.ComplaintRequestDTO;
import com.cdac.dao.ComplaintDao;
import com.cdac.dao.UserDao;
import com.cdac.entities.Complaint;
import com.cdac.entities.User;



@Service
public class ComplaintService {
	
	    @Autowired
	    private ComplaintDao complaintDao ;
	    @Autowired
	    private UserDao userDao;
	    @Autowired
	    private ModelMapper modelMapper;
	    
	    @Value("${file.upload-dir}")
	    private String uploadDir;

	    public Complaint createComplaint(ComplaintRequestDTO request, MultipartFile file) throws IOException {

	        User reporter = userDao.findById(request.getReporterId())
	                .orElseThrow(() -> new RuntimeException("Reporter not found"));

	        // Save the image
	        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
	        Path filePath = Paths.get(uploadDir, fileName);
	        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

	        // Map DTO to Entity
	        Complaint complaint = modelMapper.map(request, Complaint.class);

	        // Set remaining fields manually
	        complaint.setReporter(reporter);
	        complaint.setImageUrl(filePath.toString());
	        complaint.setStatus("PENDING");
	        complaint.setToken(UUID.randomUUID().toString().substring(0, 8).toUpperCase());
	        complaint.setCreatedAt(LocalDateTime.now());
	        complaint.setUpdatedAt(LocalDateTime.now());

	        return complaintDao.save(complaint);
	    }
	    
	    
	    
	    public List<Complaint> getAllComplaints() {
	        return complaintDao.findAll();
	    }
	    
	    public List<Complaint> getComplaintsByReporterId(Long reporterId) {
	        return complaintDao.findByReporterId(reporterId);
	    }
	    
	    
	    public Complaint assignComplaint(Long complaintId, Long assignedToUserId) {
	        Complaint complaint = complaintDao.findById(complaintId)
	                .orElseThrow(() -> new RuntimeException("Complaint not found"));

	        User assignedUser = userDao.findById(assignedToUserId)
	                .orElseThrow(() -> new RuntimeException("Assigned user not found"));

	        complaint.setAssignedTo(assignedToUserId);
	        complaint.setUpdatedAt(LocalDateTime.now());

	        return complaintDao.save(complaint);
	    }
	    
	    public void deleteComplaintById(Long id) {
	        Complaint complaint = complaintDao.findById(id)
	                .orElseThrow(() -> new RuntimeException("Complaint not found with ID: " + id));

	        complaintDao.delete(complaint);
	    }
	 
	    public Complaint updateStatus(Long id, String newStatus) {
	        Complaint complaint = complaintDao.findById(id)
	                .orElseThrow(() -> new RuntimeException("Complaint not found"));

	        complaint.setStatus(newStatus); // assuming status is a String field in Complaint entity
	        return complaintDao.save(complaint);
	    }
	    
	    
	    
	    
	    
	    
	    
	    
	    
	}


	 


