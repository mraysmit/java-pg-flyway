package dev.mars.javapgflyway.controller;

import dev.mars.javapgflyway.entity.Bookmark;
import dev.mars.javapgflyway.repository.BookmarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bookmarks")
public class BookmarkController {

    private final BookmarkRepository bookmarkRepository;

    @Autowired
    public BookmarkController(BookmarkRepository bookmarkRepository) {
        this.bookmarkRepository = bookmarkRepository;
    }

    @GetMapping
    public List<Bookmark> getAllBookmarks() {
        return bookmarkRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bookmark> getBookmarkById(@PathVariable Long id) {
        Optional<Bookmark> bookmark = bookmarkRepository.findById(id);
        return bookmark.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search/title")
    public List<Bookmark> searchByTitle(@RequestParam String query) {
        return bookmarkRepository.findByTitleContainingIgnoreCase(query);
    }

    @GetMapping("/search/url")
    public List<Bookmark> searchByUrl(@RequestParam String query) {
        return bookmarkRepository.findByUrlContaining(query);
    }

    @GetMapping("/favorites")
    public List<Bookmark> getFavorites() {
        return bookmarkRepository.findByFavoriteTrue();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Bookmark createBookmark(@RequestBody Bookmark bookmark) {
        return bookmarkRepository.save(bookmark);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bookmark> updateBookmark(@PathVariable Long id, @RequestBody Bookmark bookmarkDetails) {
        return bookmarkRepository.findById(id)
                .map(existingBookmark -> {
                    existingBookmark.setTitle(bookmarkDetails.getTitle());
                    existingBookmark.setUrl(bookmarkDetails.getUrl());
                    existingBookmark.setDescription(bookmarkDetails.getDescription());
                    existingBookmark.setFavorite(bookmarkDetails.isFavorite());
                    return ResponseEntity.ok(bookmarkRepository.save(existingBookmark));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookmark(@PathVariable Long id) {
        return bookmarkRepository.findById(id)
                .map(bookmark -> {
                    bookmarkRepository.delete(bookmark);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}