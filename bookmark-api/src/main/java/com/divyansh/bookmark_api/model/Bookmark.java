package com.divyansh.bookmark.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.LocalDateTime

//Bookmark entity class

@Document(collection = "bookmarks") //MongoDB collection
@Data //Lombok automatically creates the getter,setter,toString and equals
@NoArgsConstructor //Lombok constructor with no arguments
@AllArgsConstructor //Lombok constructor with all parameters

public class Bookmark {
    @Id //marks id as the primary key in the db
    private String id;
    private String title;
    private String url;
    private String description;
    private String[] tags;
    private LocalDateTime createdAt;
    
}