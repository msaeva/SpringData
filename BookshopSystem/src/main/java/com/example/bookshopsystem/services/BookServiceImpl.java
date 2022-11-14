package com.example.bookshopsystem.services;

import com.example.bookshopsystem.entities.AgeRestriction;
import com.example.bookshopsystem.entities.Book;
import com.example.bookshopsystem.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findByReleaseDateAfter(LocalDate releaseDate) {
        return bookRepository.findByReleaseDateAfter(releaseDate);
    }

    @Override
    public int countByReleaseDateAfter(LocalDate releaseDate) {
        return bookRepository.countByReleaseDateAfter(releaseDate);
    }

    @Override
    public List<Book> findBooksByAgeRestriction(AgeRestriction ageRestriction) {
        return bookRepository.findBooksByAgeRestriction(ageRestriction);
    }

    @Override
    public List<Book> findAllBooksWithLessThan5000Copies(int copies) {
        return bookRepository.findAllByCopiesLessThan(copies);
    }

    @Override
    public List<Book> findByGivenPrice(BigDecimal lessThan, BigDecimal greaterThan) {
        return bookRepository.findAllByPriceGreaterThanAndLessThan(lessThan, greaterThan);
    }

    @Override
    public List<Book> findAllByNotReleasedDate(LocalDate releaseDate) {
        return bookRepository.findAllByReleaseDateNot(releaseDate);
    }

    @Override
    public List<Book> findAllByReleaseDateBefore(LocalDate releaseDate) {
        return bookRepository.findAllByReleaseDateBefore(releaseDate);
    }

    @Override
    public List<Book> findByTitleContainingGivenString(String substring) {
        return bookRepository.findAllByTitleContainingIgnoreCase(substring);
    }

    @Override
    public List<Book> findAllByAuthorLastNameStartingWith(String startString) {
        return bookRepository.findAllByAuthorLastNameStartingWith(startString);
    }

    @Override
    public int findByTitleCountGreaterThan(int number) {
        return bookRepository.findByTitleCountGreaterThan(number);
    }


}
