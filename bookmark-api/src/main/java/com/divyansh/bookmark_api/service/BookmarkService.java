package com.divyansh.bookmark_api.service;

import org.springframework.stereotype.Service;
import com.divyansh.bookmark_api.model.Bookmark;
import com.divyansh.bookmark_api.repository.BookmarkRepository;
import java.util.Optional;
import java.util.List;

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
        throw new RuntimeException("Bookmark not found");
    }

    public Bookmark save(Bookmark bookmark) {
        return bookmarkRepository.save(bookmark);
    }

    public void deleteById(String id) {
        bookmarkRepository.deleteById(id);
    }

    public List<Bookmark> findAll() {
        return bookmarkRepository.findAll();
    }
} 