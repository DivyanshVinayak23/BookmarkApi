package com.divyansh.bookmark_api.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.divyansh.bookmark_api.model.Bookmark;
import com.divyansh.bookmark_api.service.BookmarkService;
import java.util.List;

@RestController
@RequestMapping("/bookmarks")
@CrossOrigin(origins = "http://localhost:8080")
public class BookmarkController {
    private final BookmarkService bookmarkService;

    public BookmarkController(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }

    @GetMapping("/{id}")
    public Bookmark getById(@PathVariable String id) {
        return bookmarkService.getById(id);
    }

    @PostMapping
    public Bookmark create(@RequestBody Bookmark bookmark) {
        return bookmarkService.save(bookmark);
    }

    @PutMapping("/{id}")
    public Bookmark update(@PathVariable String id, @RequestBody Bookmark bookmark) {
        bookmark.setId(id);
        return bookmarkService.save(bookmark);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        bookmarkService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<Bookmark> getAll() {
        return bookmarkService.findAll();
    }
}
