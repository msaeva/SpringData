package com.example.bookshopsystem.services;

import com.example.bookshopsystem.entities.AgeRestriction;
import com.example.bookshopsystem.entities.Book;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public interface BookService {
    List<Book> findByReleaseDateAfter(LocalDate releaseDate);

    int countByReleaseDateAfter(LocalDate releaseDate);

    List<Book> findBooksByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findAllBooksWithLessThan5000Copies(int copies);
    List<Book> findByGivenPrice(BigDecimal lessThan, BigDecimal greaterThan);

    List<Book> findAllByNotReleasedDate(LocalDate releaseDate);
    List<Book> findAllByReleaseDateBefore(LocalDate releaseDate);

    List<Book> findByTitleContainingGivenString(String substring);
    List<Book> findAllByAuthorLastNameStartingWith(String startString);
    int findByTitleCountGreaterThan(int number);
}
