package com.fm.progresstracker.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "Visitor")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Visitor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer visitorId;

    @Column(nullable = false, length = 50)
    private String visitorName;

    @Column(nullable = false, unique = true, length = 100)
    private String visitorEmail;

    private String feedBackMessage;

    @CreationTimestamp
    private LocalDateTime createdAt;

}