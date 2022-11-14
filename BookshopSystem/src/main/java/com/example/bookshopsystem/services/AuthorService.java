package com.example.bookshopsystem.services;

import com.example.bookshopsystem.entities.Author;

import java.time.LocalDate;
import java.util.List;

public interface AuthorService {
    Author getRandomAuthor();
    List<Author> findDistinctByBooksReleaseDateBefore(LocalDate releaseDate);
    List<Author> findAllByFirstNameEndingWith(String ends);
    List<Author> getTotalBookCopiesByAuthor();
}
