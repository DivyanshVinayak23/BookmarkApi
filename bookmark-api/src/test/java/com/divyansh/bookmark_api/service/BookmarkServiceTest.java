package com.divyansh.bookmark_api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.divyansh.bookmark_api.model.Bookmark;
import com.divyansh.bookmark_api.repository.BookmarkRepository;
import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
public class BookmarkServiceTest {
    @Mock
    private BookmarkRepository bookmarkRepository;
    @Test
    public void testGetBookmarkById(){
        BookmarkService bookmarkService = new BookmarkService(bookmarkRepository);
        Bookmark testBookmark = Bookmark.builder()
            .id("1")
            .title("Test Bookmark")
            .url("https://www.google.com")
            .description("Test Description")
            .tags(new String[]{"test", "bookmark"})
            .createdAt(LocalDateTime.now())
            .build();
        Optional<Bookmark> bookmark = Optional.of(testBookmark);
        when(bookmarkRepository.findById("1")).thenReturn(bookmark);
        Bookmark result = bookmarkService.getById("1");
        assertEquals("Test Bookmark", result.getTitle());
        verify(bookmarkRepository, times(1)).findById("1");
    }
}
