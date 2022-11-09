package com.example.booshop.services.book;

import com.example.booshop.domain.entities.Book;

import java.time.LocalDate;
import java.util.List;

public interface BookService {
    void seedBooks(List<Book> books);

    List<Book> findAllBooksByReleaseDateAfter(LocalDate localDate);
}
