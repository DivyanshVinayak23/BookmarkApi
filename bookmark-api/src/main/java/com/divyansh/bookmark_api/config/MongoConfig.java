package com.divyansh.bookmark_api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoAuditing
@EnableMongoRepositories(basePackages = "com.divyansh.bookmark_api.repository")
//Basic configuration for MongoDB
public class MongoConfig {

} 