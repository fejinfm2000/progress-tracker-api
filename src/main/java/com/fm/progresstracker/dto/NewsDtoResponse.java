package com.fm.progresstracker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewsDtoResponse {
    private String status;
    private String message;
    private PaginationDto pagination;
    private List<NewsDataDto> data;

}
