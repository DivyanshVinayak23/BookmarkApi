package com.divyansh.bookmark_api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.divyansh.bookmark_api.model.Bookmark;
import java.util.Scanner;

@SpringBootApplication
public class KafkaTestProducer {

    public static void main(String[] args) {
        SpringApplication.run(KafkaTestProducer.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(KafkaTemplate<String, String> kafkaTemplate) {
        return args -> {
            Scanner scanner = new Scanner(System.in);
            ObjectMapper mapper = new ObjectMapper();

            while (true) {
                System.out.println("\nEnter bookmark details (or 'exit' to quit):");
                System.out.print("Title: ");
                String title = scanner.nextLine();
                
                if ("exit".equalsIgnoreCase(title)) {
                    break;
                }

                System.out.print("URL: ");
                String url = scanner.nextLine();

                System.out.print("Description: ");
                String description = scanner.nextLine();

                System.out.print("Tags (comma-separated): ");
                String[] tags = scanner.nextLine().split(",");

                Bookmark bookmark = Bookmark.builder()
                    .title(title)
                    .url(url)
                    .description(description)
                    .tags(tags)
                    .build();

                String bookmarkJson = mapper.writeValueAsString(bookmark);
                kafkaTemplate.send("bookmark-topic", bookmarkJson);
                System.out.println("Bookmark sent to Kafka!");
            }

            scanner.close();
        };
    }
} 