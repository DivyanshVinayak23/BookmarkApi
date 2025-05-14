package com.divyansh.bookmark_api.dto;

import org.springframework.data.annotation.Id;
import java.time.LocalDateTime;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor


//Defination class for the bookmark data transfer object
//Possible improvement: don't leak all the entity fields in the dto, only expose what is needed for the client to use it.

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
