package com.fm.progresstracker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "Resources")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer resourceId;

    @ManyToOne
    @JoinColumn(name = "activityId", nullable = false)
    private Activity activity;

    @Column(nullable = false, length = 100)
    private String resourceName;

    @Column(length = 50)
    private String resourceType;

    @Column(columnDefinition = "TEXT")
    private String resourceURL;

    @Column(nullable = false)
    private boolean isCompleted;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private LocalDateTime createdAt;

}