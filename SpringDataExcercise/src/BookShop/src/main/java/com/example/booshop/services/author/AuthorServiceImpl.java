package com.example.booshop.services.author;

import com.example.booshop.domain.entities.Author;
import com.example.booshop.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void seedAuthors(List<Author> authors) {
        this.authorRepository.saveAll(authors);
    }

    @Override
    public boolean isDataSeeded() {
        return this.authorRepository.count() > 0;
    }

    @Override
    public Author getRandomAuthor() {
        final long count = this.authorRepository.count();
        if (count != 0) {
            Long randomAuthorId = new Random().nextLong(1L, count);
            return this.authorRepository.findAuthorBy(randomAuthorId).orElseThrow(NoSuchElementException::new);
        }
        throw new RuntimeException();
    }


    @Override
    public List<Author> findDistinctByBooksBefore(LocalDate date) {
        return this.authorRepository.findDistinctByBooksReleaseDateBefore(date).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Author> findAllAuthorsOrderByBooks() {
        return new ArrayList<>();
        //return this.authorRepository.findAllAuthorsOrderByBooks().orElseThrow(NoSuchElementException::new);
    }
}
