package com.fm.progresstracker.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VisitorDto {
    private Integer visitorId;
    private String visitorName;
    private String visitorEmail;
    private String feedBackMessage;
    private LocalDateTime createdAt;
}