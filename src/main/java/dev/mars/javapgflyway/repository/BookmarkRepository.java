package dev.mars.javapgflyway.repository;

import dev.mars.javapgflyway.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    
    // Find bookmarks by title containing the given string (case insensitive)
    List<Bookmark> findByTitleContainingIgnoreCase(String title);
    
    // Find bookmarks by URL containing the given string
    List<Bookmark> findByUrlContaining(String url);
    
    // Find favorite bookmarks
    List<Bookmark> findByFavoriteTrue();
}