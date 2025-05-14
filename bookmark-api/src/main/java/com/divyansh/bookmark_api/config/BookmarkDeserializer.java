package com.divyansh.bookmark_api.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.divyansh.bookmark_api.model.Bookmark;
import org.apache.kafka.common.serialization.Deserializer;
import java.io.IOException;

//This class is used to deserialize the bookmark coming from the kafka stream
//BookmarkDeserializer is a class that implements the Deserializer interface from Kafka Streams API.


public class BookmarkDeserializer implements Deserializer<Bookmark> {
    private final ObjectMapper objectMapper = new ObjectMapper(); //ObjectMapper is used to deserialize the bookmark coming from the kafka stream
    //It consists of a json factory, a serializer and a deserializer

    @Override
    public Bookmark deserialize(String topic, byte[] data) {
        try {
            if (data == null) {
                return null;
            }
            return objectMapper.readValue(data, Bookmark.class); //readValue method is used to deserialize the byte object into a Bookmark class
        } catch (IOException e) {
            throw new RuntimeException("Error deserializing Bookmark", e);
        }
    }
} 