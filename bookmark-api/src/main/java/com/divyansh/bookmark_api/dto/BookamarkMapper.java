package com.divyansh.bookmark_api.dto;
import com.divyansh.bookmark_api.model.Bookmark;

public class BookamarkMapper {
    public static BookmarkDto toDto(Bookmark bookmark) {
        BookmarkDto bookmarkDto = new BookmarkDto();
        bookmarkDto.setId(bookmark.getId());
        bookmarkDto.setTitle(bookmark.getTitle());
        bookmarkDto.setUrl(bookmark.getUrl());
        bookmarkDto.setDescription(bookmark.getDescription());
        bookmarkDto.setTags(bookmark.getTags());
        bookmarkDto.setCreatedAt(bookmark.getCreatedAt());
        bookmarkDto.setUpdatedAt(bookmark.getUpdatedAt());
        return bookmarkDto;
    }
    public static Bookmark toEntity(BookmarkDto bookmarkDto) {
        Bookmark bookmark = new Bookmark();
        bookmark.setId(bookmarkDto.getId());
        bookmark.setTitle(bookmarkDto.getTitle());
        bookmark.setUrl(bookmarkDto.getUrl());
        bookmark.setDescription(bookmarkDto.getDescription());
        bookmark.setTags(bookmarkDto.getTags());
        bookmark.setCreatedAt(bookmarkDto.getCreatedAt());
        bookmark.setUpdatedAt(bookmarkDto.getUpdatedAt());
        return bookmark;
    }
}
