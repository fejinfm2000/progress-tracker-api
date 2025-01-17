package com.fm.progresstracker.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fm.progresstracker.entity.Category;
import com.fm.progresstracker.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivityDto {
    private Integer activityId;
    private Category category;
    private User user;
    private String activityName;
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate endDate;
    private String status;
    private BigDecimal progress;
    private LocalDateTime createdAt;

}
