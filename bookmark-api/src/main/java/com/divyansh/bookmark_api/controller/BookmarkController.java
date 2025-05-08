package com.divyansh.bookmark_api.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.divyansh.bookmark_api.model.Bookmark;
import com.divyansh.bookmark_api.service.BookmarkService;
import java.util.List;

// This class is a Spring REST controller that handles HTTP requests for bookmarks
@RestController
// All endpoints in this controller will be prefixed with "/bookmarks"
@RequestMapping("/bookmarks")
// Allows cross-origin requests from http://localhost:8080 (for frontend applications)
@CrossOrigin(origins = "http://localhost:8080")
public class BookmarkController {
    // Service that contains the business logic for bookmarks
    private final BookmarkService bookmarkService;

    // Constructor that Spring uses to inject the BookmarkService dependency
    public BookmarkController(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }

    // Handles GET requests to fetch a single bookmark by its ID
    // Example: GET /bookmarks/123
    @GetMapping("/{id}")
    public Bookmark getById(@PathVariable String id) {
        return bookmarkService.getById(id);
    }

    // Handles POST requests to create a new bookmark
    // The bookmark data is sent in the request body as JSON
    @PostMapping
    public Bookmark create(@RequestBody Bookmark bookmark) {
        return bookmarkService.save(bookmark);
    }

    // Handles PUT requests to update an existing bookmark
    // The ID is in the URL path and the updated data is in the request body
    @PutMapping("/{id}")
    public Bookmark update(@PathVariable String id, @RequestBody Bookmark bookmark) {
        // Set the ID from the URL path to the bookmark object before saving
        bookmark.setId(id);
        return bookmarkService.save(bookmark);
    }

    // Handles DELETE requests to remove a bookmark by its ID
    // Returns an empty response with HTTP status 204 (No Content)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        bookmarkService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Handles GET requests to fetch all bookmarks
    // Example: GET /bookmarks
    @GetMapping
    public List<Bookmark> getAll() {
        return bookmarkService.findAll();
    }
}