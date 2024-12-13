package com.fm.progresstracker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "ProgressLogs")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class ProgressLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer logId;

    @ManyToOne
    @JoinColumn(name = "activityId", nullable = false)
    private Activity activity;

    @ManyToOne
    @JoinColumn(name = "subActivityId")
    private SubActivity subActivity;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private LocalDateTime loggedAt;

    @Column(precision = 5, scale = 2)
    private BigDecimal progress;

    @ManyToOne
    @JoinColumn(name = "updatedBy", nullable = false)
    private User updatedBy;

    @Column(length = 255)
    private String remarks;

}