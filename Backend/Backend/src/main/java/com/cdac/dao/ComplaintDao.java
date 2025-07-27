package com.cdac.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdac.entities.Complaint;

public interface ComplaintDao extends JpaRepository<Complaint, Long> {

	List<Complaint> findByReporterId(Long reporterId);
}
