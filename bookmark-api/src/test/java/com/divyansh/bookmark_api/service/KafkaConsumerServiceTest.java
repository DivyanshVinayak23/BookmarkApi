package com.divyansh.bookmark_api.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.core.KafkaTemplate;
import com.divyansh.bookmark_api.model.Bookmark;
import com.divyansh.bookmark_api.repository.BookmarkRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@EmbeddedKafka(partitions = 1, topics = {"bookmark-topic"})
public class KafkaConsumerServiceTest {

    @Autowired
    private EmbeddedKafkaBroker kafkaBroker;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Test
    public void testConsumeBookmark() throws Exception {
        // Create a test bookmark
        Bookmark testBookmark = Bookmark.builder()
            .title("Test Bookmark")
            .url("https://test.com")
            .description("Test Description")
            .tags(new String[]{"test", "kafka"})
            .build();

        // Convert to JSON
        ObjectMapper mapper = new ObjectMapper();
        String bookmarkJson = mapper.writeValueAsString(testBookmark);

        // Send to Kafka
        kafkaTemplate.send("bookmark-topic", bookmarkJson);

        // Wait for processing
        TimeUnit.SECONDS.sleep(2);

        // Verify the bookmark was saved
        Bookmark savedBookmark = bookmarkRepository.findByTitle("Test Bookmark")
            .orElseThrow(() -> new RuntimeException("Bookmark not found"));
        
        assertNotNull(savedBookmark);
        assertNotNull(savedBookmark.getId());
    }
} 