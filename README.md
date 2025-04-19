# Bookmark Manager - Spring Boot with JPA, PostgreSQL, and Flyway

A simple Spring Boot application that demonstrates the use of Spring Data JPA, PostgreSQL, and Flyway for database migrations to manage browser bookmarks.

## Features

- RESTful API for managing bookmarks
- JPA entity with proper annotations including @SequenceGenerator and @GeneratedValue
- PostgreSQL database integration
- Flyway database migrations
- Sample data loader for development

## Prerequisites

- Java 24 or later
- Maven
- PostgreSQL database

## Setup

1. Create a PostgreSQL database named `bookmarks_db`:
   ```sql
   CREATE DATABASE bookmarks_db;
   ```

2. Configure the database connection in `src/main/resources/application.properties` if needed:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/bookmarks_db
   spring.datasource.username=postgres
   spring.datasource.password=postgres
   ```

3. Build the application:
   ```bash
   mvn clean install
   ```

4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

## API Endpoints

| Method | URL                          | Description                                |
|--------|------------------------------|--------------------------------------------|
| GET    | /api/bookmarks              | Get all bookmarks                          |
| GET    | /api/bookmarks/{id}         | Get bookmark by ID                         |
| GET    | /api/bookmarks/search/title | Search bookmarks by title                  |
| GET    | /api/bookmarks/search/url   | Search bookmarks by URL                    |
| GET    | /api/bookmarks/favorites    | Get all favorite bookmarks                 |
| POST   | /api/bookmarks              | Create a new bookmark                      |
| PUT    | /api/bookmarks/{id}         | Update an existing bookmark                |
| DELETE | /api/bookmarks/{id}         | Delete a bookmark                          |

## Example Requests

### Create a new bookmark
```bash
curl -X POST http://localhost:8080/api/bookmarks \
  -H "Content-Type: application/json" \
  -d '{"title":"Example Bookmark","url":"https://example.com","description":"An example bookmark","favorite":false}'
```

### Get all bookmarks
```bash
curl http://localhost:8080/api/bookmarks
```

### Search bookmarks by title
```bash
curl http://localhost:8080/api/bookmarks/search/title?query=Spring
```

## Project Structure

- `src/main/java/dev/mars/javapgflyway/`
  - `JavaPgFlywayApplication.java` - Main application class
  - `entity/Bookmark.java` - JPA entity with annotations
  - `repository/BookmarkRepository.java` - Spring Data JPA repository
  - `controller/BookmarkController.java` - REST controller
  - `config/DataLoader.java` - Sample data loader for development

- `src/main/resources/`
  - `application.properties` - Application configuration
  - `db/migration/` - Flyway migration scripts

## Database Schema

The application uses a single table `bookmarks` with the following structure:

- `id` - Primary key, auto-generated using a sequence
- `title` - Bookmark title (required)
- `url` - Bookmark URL (required)
- `description` - Optional description
- `created_at` - Creation timestamp
- `updated_at` - Last update timestamp
- `is_favorite` - Boolean flag for favorite bookmarks

## Development

The application runs with the `dev` profile by default, which loads sample data on startup. To disable this behavior, change the active profile in `application.properties`:

```properties
spring.profiles.active=prod
```