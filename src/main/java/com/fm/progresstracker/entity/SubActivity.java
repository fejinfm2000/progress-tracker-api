package com.fm.progresstracker.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "SubActivities")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class SubActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer subActivityId;

    @ManyToOne
    @JoinColumn(name = "activityId", nullable = false)
    private Activity activity;

    @Column(nullable = false, length = 100)
    private String subActivityName;

    @Column(columnDefinition = "TEXT")
    private String description;

    private LocalDate startDate;
    private LocalDate endDate;

    @Column(precision = 5, scale = 2)
    private BigDecimal progress;

    @Column(length = 20)
    private String status;

}