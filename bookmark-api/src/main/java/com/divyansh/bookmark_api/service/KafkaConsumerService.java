package com.divyansh.bookmark_api.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.divyansh.bookmark_api.model.Bookmark;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;

import org.springframework.messaging.handler.annotation.Payload;

//Fixme: KafkaConsumerService is directly using the repository, which is not the right practice as it skips over the business logic

@Service
public class KafkaConsumerService {

    private final BookmarkService bookmarkService;
    private final ObjectMapper objectMapper;

    @Autowired
    public KafkaConsumerService(BookmarkService bookmarkService, ObjectMapper objectMapper) {
        this.bookmarkService = bookmarkService;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "bookmark-topic", groupId = "bookmark", containerFactory = "kafkaListenerContainerFactory")
    public void consumeMessage(@Payload String message) {
        try {
            System.out.println("Received message: " + message);
            
            // Try to convert from JSON to Bookmark object
            Bookmark bookmark = objectMapper.readValue(message, Bookmark.class);
            
            // Set or update timestamps
            if (bookmark.getCreatedAt() == null) {
                bookmark.setCreatedAt(LocalDateTime.now());
            }
            bookmark.setUpdatedAt(LocalDateTime.now());
            
            // Log the received bookmark
            System.out.println("Processing bookmark: " + bookmark.getTitle());
            
            // Save using the service
            Bookmark savedBookmark = bookmarkService.save(bookmark);
            System.out.println("Bookmark saved with ID: " + savedBookmark.getId());
            
        } catch (Exception e) {
            System.err.println("Error processing bookmark: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
