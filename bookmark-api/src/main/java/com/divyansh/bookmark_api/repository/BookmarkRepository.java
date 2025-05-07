package com.divyansh.bookmark_api.repository;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import com.divyansh.bookmark_api.model.Bookmark;
import java.util.Optional;
import java.util.List;

@Repository
public class BookmarkRepository {
    private final MongoTemplate mongoTemplate;

    public BookmarkRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Optional<Bookmark> findById(String id) {
        return Optional.ofNullable(mongoTemplate.findById(id, Bookmark.class));
    }

    public Bookmark save(Bookmark bookmark) {
        return mongoTemplate.save(bookmark);
    }

    public void deleteById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, Bookmark.class);
    }

    public List<Bookmark> findAll() {
        return mongoTemplate.findAll(Bookmark.class);
    }
}
