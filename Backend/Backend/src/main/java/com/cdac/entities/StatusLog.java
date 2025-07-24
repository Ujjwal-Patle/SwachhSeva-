package com.cdac.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "status_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatusLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Complaint relationship
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "complaint_id", nullable = false)
    private Complaint complaint;

    private String status; // Example: "PENDING", "IN_PROGRESS", "RESOLVED"

    @Column(columnDefinition = "TEXT")
    private String notes;

    // Updated by (user who changed the status)
    @Column(name = "updated_by")
    private Long updatedBy;

    @Column(name = "updated_at", updatable = false)
    private LocalDateTime updatedAt;

  
}

