package com.cdac.entities;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "complaints")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Reporter relationship
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reporter_id", nullable = false)
    private User reporter;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String category;

    private String city;

    @Column(name = "location_lat")
    private Double locationLat;

    @Column(name = "location_long")
    private Double locationLong;

    @Column(name = "image_url")
    private String imageUrl;

    private String status;  // e.g., "PENDING", "IN_PROGRESS", "RESOLVED"

    // Assigned to (another user/volunteer/supervisor)
    @Column(name = "assigned_to")
    private Long assignedTo;

    @Column(unique = true, length = 50)
    private String token;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
  
}
