package com.example.booshop;

import com.example.booshop.domain.entities.Book;
import com.example.booshop.services.author.AuthorService;
import com.example.booshop.services.book.BookService;
import com.example.booshop.services.seed.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final LocalDate BOOK_YEAR = LocalDate.of(2000, 1, 1);
    private final LocalDate BOOKS_YEAR_BEFORE = LocalDate.of(1999, 1, 1);

    private final SeedService seedService;
    private final BookService bookService;
    private final AuthorService authorService;


    @Autowired
    public ConsoleRunner(SeedService seedService, BookService bookService, AuthorService authorService) {
        this.seedService = seedService;
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.seedService.seedAllData();

        this.getAllBookAfterAGivenYear();
       // this.getAllAuthorsWithReleaseDateBefore();
    }

    private void getAllBookAfterAGivenYear() {
        List<Book> allBooksByReleaseDateAfter = this.bookService.findAllBooksByReleaseDateAfter(BOOK_YEAR);
        allBooksByReleaseDateAfter.forEach(book -> System.out.println(book.getTitle()));
    }

    private void getAllAuthorsWithReleaseDateBefore() {
        this.authorService.findDistinctByBooksBefore(BOOKS_YEAR_BEFORE)
                .forEach(a -> System.out.println(a.getFirstName() + " " + a.getLastName()));
    }

    private void getAllOrderByBooks() {
        this.authorService.findAllAuthorsOrderByBooks()
                .forEach(a -> System.out.println(a.getFirstName() + " "
                        + a.getLastName() + " " + a.getBooks().size()));
    }
}
