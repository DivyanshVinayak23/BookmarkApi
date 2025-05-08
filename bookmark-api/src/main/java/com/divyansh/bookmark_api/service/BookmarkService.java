package com.divyansh.bookmark_api.service;

import org.springframework.stereotype.Service;
import com.divyansh.bookmark_api.model.Bookmark;
import com.divyansh.bookmark_api.repository.BookmarkRepository;
import java.util.Optional;
import java.util.List;
import java.time.LocalDateTime;

// The service will contain the buisness logic for the API
//Encapsulates the Repository
@Service
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;

    public BookmarkService(BookmarkRepository bookmarkRepository) {
        this.bookmarkRepository = bookmarkRepository;
    }

    public Bookmark getById(String id) {
        Optional<Bookmark> bookmark = bookmarkRepository.findById(id);
        if(bookmark.isPresent()) {
            return bookmark.get();
        }
        throw new RuntimeException("Bookmark not found with id: " + id);
    }

    public Bookmark save(Bookmark bookmark) {
        if (bookmark.getId() == null) {
            // New bookmark
            bookmark.setCreatedAt(LocalDateTime.now());
        }
        bookmark.setUpdatedAt(LocalDateTime.now());
        return bookmarkRepository.save(bookmark);
    }

    public void deleteById(String id) {
        if (!bookmarkRepository.findById(id).isPresent()) {
            throw new RuntimeException("Bookmark not found with id: " + id);
        }
        bookmarkRepository.deleteById(id);
    }

    public List<Bookmark> findAll() {
        return bookmarkRepository.findAll();
    }
} 