package com.example.booshop.services.seed;

import com.example.booshop.domain.entities.Author;
import com.example.booshop.domain.entities.Book;
import com.example.booshop.domain.entities.Category;
import com.example.booshop.domain.enums.AgeRestriction;
import com.example.booshop.domain.enums.EditionType;
import com.example.booshop.services.author.AuthorService;
import com.example.booshop.services.book.BookService;
import com.example.booshop.services.category.CategoryService;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.booshop.Constants.FilePath.*;

@Component
public class SeedServiceImpl implements SeedService {
    private final AuthorService authorService;
    private final BookService bookService;
    private final CategoryService categoryService;

    public SeedServiceImpl(AuthorService authorService,
                           BookService bookService,
                           CategoryService categoryService) {

        this.authorService = authorService;
        this.bookService = bookService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedAuthors() throws IOException {
        if (!this.authorService.isDataSeeded()) {
            this.authorService.seedAuthors(Files.readAllLines(Path.of(RESOURCE_URL + AUTHOR_FILE_NAME)).stream().filter(s -> !s.isBlank())
                    .map(s -> Author.builder()
                            .firstName(s.split("\\s+")[0])
                            .lastName(s.split("\\s+")[1]).build())
                    .collect(Collectors.toList()));
        }
    }

    @Override
    public void seedBooks() throws IOException {
        List<Book> books = Files.readAllLines(Path.of(RESOURCE_URL + BOOK_FILE_NAME))
                .stream().map(line -> {
                    String[] data = line.split("\\s+");

                    Author author = this.authorService.getRandomAuthor();

                    EditionType editionType = EditionType.values()[Integer.parseInt(data[0])];

                    LocalDate releaseDate = LocalDate.parse(data[1], DateTimeFormatter.ofPattern(("d/M/yyyy")));

                    int copies = Integer.parseInt(data[2]);

                    BigDecimal price = new BigDecimal(data[3]);

                    AgeRestriction ageRestriction = AgeRestriction
                            .values()[Integer.parseInt(data[4])];

                    String title = Arrays.stream(data)
                            .skip(5)
                            .collect(Collectors.joining("\\s+"));
                    Set<Category> categories = categoryService.getRandomCategories();


                    return Book.builder()
                            .title(title)
                            .editionType(editionType)
                            .price(price)
                            .releaseDate(releaseDate)
                            .ageRestriction(ageRestriction)
                            .author(author)
                            .categories(categories)
                            .copies(copies).build();


                }).collect(Collectors.toList());

        this.bookService.seedBooks(books);

    }

    @Override
    public void seedCategories() throws IOException {
        if (!this.categoryService.isDateSeeded()) {
            this.categoryService.seedCategories(Files.readAllLines(Path.of(RESOURCE_URL + CATEGORY_FILE_NAME))
                    .stream().filter(s -> !s.isBlank())
                    .map(name -> Category.builder().name(name).build()).collect(Collectors.toList()));
        }
    }
}
