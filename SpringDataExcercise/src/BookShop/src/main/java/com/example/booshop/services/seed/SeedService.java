package com.example.booshop.services.seed;

import java.io.IOException;

public interface SeedService {
    void seedAuthors() throws IOException;
    void seedBooks() throws IOException;
    void seedCategories() throws IOException;

    default void seedAllData() throws IOException {
        seedCategories();
        seedAuthors();
        seedBooks();

    }
}
