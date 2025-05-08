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
        Optional<Bookmark> bookmark = Optional.of(new Bookmark("1", "Test Bookmark", "https://www.google.com", "Test Description", new String[]{"test", "bookmark"}, LocalDateTime.now()));
        when(bookmarkRepository.findById("1")).thenReturn(bookmark);
        Bookmark result = bookmarkService.getById("1");
        assertEquals("Test Bookmark", result.getTitle());
        verify(bookmarkRepository, times(1)).findById("1");
    }
}
