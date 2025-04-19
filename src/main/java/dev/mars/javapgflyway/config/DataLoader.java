package dev.mars.javapgflyway.config;

import dev.mars.javapgflyway.entity.Bookmark;
import dev.mars.javapgflyway.repository.BookmarkRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;
import java.util.Arrays;

@Configuration
public class DataLoader {

    /**
     * Loads sample data only when the "dev" profile is active
     * To activate, add spring.profiles.active=dev to application.properties
     * or pass --spring.profiles.active=dev as a command line argument
     */
    @Bean
    @Profile("dev")
    public CommandLineRunner initDatabase(BookmarkRepository repository) {
        return args -> {
            // Only load data if the repository is empty
            if (repository.count() == 0) {
                System.out.println("Loading sample bookmark data...");
                
                repository.saveAll(Arrays.asList(
                    new Bookmark("Spring Framework", "https://spring.io/"),
                    new Bookmark("Baeldung", "https://www.baeldung.com/"),
                    new Bookmark("Stack Overflow", "https://stackoverflow.com/"),
                    new Bookmark("GitHub", "https://github.com/"),
                    new Bookmark("PostgreSQL Documentation", "https://www.postgresql.org/docs/")
                ));
                
                // Set one bookmark as favorite
                Bookmark github = repository.findByTitleContainingIgnoreCase("GitHub").get(0);
                github.setFavorite(true);
                github.setDescription("A platform for hosting and collaborating on code");
                repository.save(github);
                
                System.out.println("Sample data loaded successfully!");
            }
        };
    }
}