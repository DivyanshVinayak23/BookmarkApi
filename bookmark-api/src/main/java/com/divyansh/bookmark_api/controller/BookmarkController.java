package com.divyansh.bookmark_api.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.divyansh.bookmark_api.model.Bookmark;
import com.divyansh.bookmark_api.service.BookmarkService;
import com.divyansh.bookmark_api.dto.BookmarkDto;
import com.divyansh.bookmark_api.dto.BookamarkMapper;
import java.util.List;
import java.util.stream.Collectors;

// This class is a Spring REST controller that handles HTTP requests for bookmarks
@RestController
// All endpoints in this controller will be prefixed with "/bookmarks"
@RequestMapping("/bookmarks")
// Allows cross-origin requests from http://localhost:8080 (for frontend applications)
@CrossOrigin(origins = "*")
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
    public BookmarkDto getById(@PathVariable String id) {
        Bookmark bookmark = bookmarkService.getById(id);
        return BookamarkMapper.toDto(bookmark);
    }

    // Handles POST requests to create a new bookmark
    // The bookmark data is sent in the request body as JSON
    @PostMapping
    public BookmarkDto create(@RequestBody BookmarkDto bookmarkDto) {
        Bookmark bookmark = BookamarkMapper.toEntity(bookmarkDto);
        Bookmark savedBookmark = bookmarkService.save(bookmark);
        return BookamarkMapper.toDto(savedBookmark);
    }

    // Handles PUT requests to update an existing bookmark
    // The ID is in the URL path and the updated data is in the request body
    @PutMapping("/{id}")
    public BookmarkDto update(@PathVariable String id, @RequestBody BookmarkDto bookmarkDto) {
        bookmarkDto.setId(id);
        Bookmark bookmark = BookamarkMapper.toEntity(bookmarkDto);
        Bookmark updatedBookmark = bookmarkService.save(bookmark);
        return BookamarkMapper.toDto(updatedBookmark);
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
    public List<BookmarkDto> getAll() {
        return bookmarkService.findAll().stream()
            .map(BookamarkMapper::toDto)
            .collect(Collectors.toList());
    }
}