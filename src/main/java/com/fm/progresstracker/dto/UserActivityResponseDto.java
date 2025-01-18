package com.fm.progresstracker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserActivityResponseDto {
    private Integer userId;
    private List<ActivityDto> activity;
    private List<SubActivityDto> subActivity;
    private List<CategoryDto> category;
    private String firstName;
    private String lastName;
    private String email;
    private String passwordHash;
    private Boolean termsAndConditionFlag;
    private LocalDateTime createdAt;
    private LocalDateTime lastLogin;

}
