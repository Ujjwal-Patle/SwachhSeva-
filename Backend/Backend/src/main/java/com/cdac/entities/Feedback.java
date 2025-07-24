package com.cdac.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "feedback")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Complaint relationship
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "complaint_id", nullable = false)
    private Complaint complaint;

    // Reporter (who gave the feedback)
    @Column(name = "reporter_id")
    private Long reporterId; // You can also make this a User entity

    // Volunteer (who resolved the complaint)
    @Column(name = "volunteer_id")
    private Long volunteerId; // Optional: can also be a User relation

    private Integer rating; // Expected range: 1 to 5

    @Column(columnDefinition = "TEXT")
    private String comment;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

  
}

