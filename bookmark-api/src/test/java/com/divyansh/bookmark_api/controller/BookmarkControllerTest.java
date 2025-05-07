package com.divyansh.bookmark_api.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.divyansh.bookmark_api.service.BookmarkService;
import com.divyansh.bookmark_api.model.Bookmark;
import java.time.LocalDateTime;

@WebMvcTest(BookmarkController.class)
public class BookmarkControllerTest {

    @MockBean
    private BookmarkService bookmarkService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetById() throws Exception {
        Bookmark bookmark = new Bookmark("1", "Test Bookmark", "https://www.google.com", "Test Description", new String[]{"test", "bookmark"}, LocalDateTime.now());
        when(bookmarkService.getById("1")).thenReturn(bookmark);

        mockMvc.perform(get("/bookmarks/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value("1"))
        .andExpect(jsonPath("$.title").value("Test Bookmark"));
        verify(bookmarkService).getById("1");
    }
} 