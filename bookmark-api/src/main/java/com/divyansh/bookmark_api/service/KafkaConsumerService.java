package com.divyansh.bookmark_api.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.divyansh.bookmark_api.model.Bookmark;
import com.divyansh.bookmark_api.repository.BookmarkRepository;
import java.time.LocalDateTime;

//Fixme: KafkaConsumerService is directly using the repository, which is not the right practice as it skips over the business logic

@Service
public class KafkaConsumerService {

    private final BookmarkRepository bookmarkRepository;

    @Autowired
    public KafkaConsumerService(BookmarkRepository bookmarkRepository) {
        this.bookmarkRepository = bookmarkRepository;
    }

    @KafkaListener(topics = "bookmark-topic", groupId = "bookmark")
    public void consumeMessage(Bookmark bookmark) {
        try {
            bookmark.setCreatedAt(LocalDateTime.now());
            bookmark.setUpdatedAt(LocalDateTime.now());
            bookmarkRepository.save(bookmark);
        } catch (Exception e) {
            System.err.println("Error processing bookmark: " + e.getMessage());
        }
    }
}
