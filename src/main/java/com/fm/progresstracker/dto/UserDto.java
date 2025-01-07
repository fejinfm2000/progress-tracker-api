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
public class UserDto {
    private Integer userId;
    private String firstName;
    private String lastName;
    private String email;
    private String passwordHash;
    private String termsAndConditionFlag;
    private LocalDateTime createdAt;
    private LocalDateTime lastLogin;
}