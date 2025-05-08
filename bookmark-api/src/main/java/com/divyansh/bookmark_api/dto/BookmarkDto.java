package com.divyansh.bookmark_api.dto;

import org.springframework.data.annotation.Id;
import java.time.LocalDateTime;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor


public class BookmarkDto {
    @Id
    private String id;
    private String title;
    private String url;
    private String description;
    private String[] tags;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
