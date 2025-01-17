package com.fm.progresstracker.dto;


import com.fm.progresstracker.entity.Activity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubActivityDto {

    private Integer subActivityId;
    private Activity activity;
    private String subActivityName;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal progress;
    private String status;

}