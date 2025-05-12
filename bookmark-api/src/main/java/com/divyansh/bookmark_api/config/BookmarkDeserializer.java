package com.divyansh.bookmark_api.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.divyansh.bookmark_api.model.Bookmark;
import org.apache.kafka.common.serialization.Deserializer;
import java.io.IOException;

public class BookmarkDeserializer implements Deserializer<Bookmark> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Bookmark deserialize(String topic, byte[] data) {
        try {
            if (data == null) {
                return null;
            }
            return objectMapper.readValue(data, Bookmark.class);
        } catch (IOException e) {
            throw new RuntimeException("Error deserializing Bookmark", e);
        }
    }
} 