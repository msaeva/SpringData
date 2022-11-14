package com.example.bookshopsystem;

import com.example.bookshopsystem.entities.AgeRestriction;
import com.example.bookshopsystem.entities.Author;
import com.example.bookshopsystem.entities.Book;
import com.example.bookshopsystem.services.AuthorService;
import com.example.bookshopsystem.services.BookService;
import com.example.bookshopsystem.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

@Component
public class ConsoleRunner implements CommandLineRunner {
    // private final SeedService seedService;

    private AuthorService authorService;
    private BookService bookService;
    private CategoryService categoryService;

    @Autowired
    public ConsoleRunner(AuthorService authorService, BookService bookService, CategoryService categoryService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
        // this.seedService.seedAll();

       countBooks_09();
    }

    private void booksAfter2000() {
        LocalDate year2000 = LocalDate.of(2000, 12, 31);

        List<Book> books = this.bookService.findByReleaseDateAfter(year2000);

        books.forEach(b -> System.out.println(b.getTitle()));

        int count = this.bookService.countByReleaseDateAfter(year2000);

        System.out.println("Total count: " + count);
    }

    private void allAuthorsWithBooksBefore1990() {
        LocalDate year1990 = LocalDate.of(1990, 1, 1);

        List<Author> authors = this.authorService.findDistinctByBooksReleaseDateBefore(year1990);

        authors.forEach(a -> System.out.println(a.getFirstName() + " " + a.getLastName()));
    }


    // Exercises: Spring Data Advanced Querying
    private void booksTitlesByAgeRestriction_01() {
        String ageRestriction = new Scanner(System.in).nextLine().toUpperCase();

        AgeRestriction parsed = AgeRestriction.valueOf(ageRestriction);
        System.out.println(parsed);

        bookService.findBooksByAgeRestriction(parsed)
                .forEach(b -> System.out.println(b.getTitle()));
    }

    private void goldenBooks_02() {
        bookService.findAllBooksWithLessThan5000Copies(5000)
                .forEach(b -> System.out.println(b.getTitle()));
    }

    private void booksByPrice_03() {
        bookService.findByGivenPrice(new BigDecimal(5), new BigDecimal(40))
                .forEach(b -> System.out.println(b.getTitle() + "-" + b.getPrice()));
    }

    private void notReleasedBooks_04() {
//        int year = new Scanner(System.in).nextInt();
//        LocalDate localDate = new LocalDate(year, 1, 1);
//
//        bookService.findAllByNotReleasedDate();
    }

    private void booksReleasedBeforeDate_05() {

    }

    private void authorsSearch() {
        String ends = new Scanner(System.in).nextLine();
        authorService.findAllByFirstNameEndingWith(ends)
                .forEach(a -> System.out.println(a.getFirstName() + " " + a.getLastName()));
    }

    private void booksSearch_07() {
        String substring = new Scanner(System.in).nextLine();

        for (Book book : bookService.findByTitleContainingGivenString(substring)) {
            System.out.println(book.getTitle());
        }
    }

    private void bookTitleSearch_08() {
        String substring = new Scanner(System.in).nextLine();

        bookService.findAllByAuthorLastNameStartingWith(substring)
                .forEach(b -> System.out.println(b.getTitle() + " " + b.getAuthor().getFirstName()
                        + " " + b.getAuthor().getLastName()));
    }

    private void countBooks_09() {
        int number = new Scanner(System.in).nextInt();

        int booksCount = bookService.findByTitleCountGreaterThan(number);

        System.out.printf("There are %s books with longer title than %d symbols%n", booksCount, number);
    }
    private void totalBookCopies_10(){

        authorService.getTotalBookCopiesByAuthor().forEach(a -> System.out.println());
    }
}
