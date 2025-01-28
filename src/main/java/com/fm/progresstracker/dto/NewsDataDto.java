package com.fm.progresstracker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewsDataDto {
    private String author;
    private String title;
    private String description;
    private String source;
    private String url;
    private String image;
    private String category;
    private String language;
    private String country;
    private String published_at;
}
