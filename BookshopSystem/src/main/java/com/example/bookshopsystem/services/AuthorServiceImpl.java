package com.example.bookshopsystem.services;

import com.example.bookshopsystem.entities.Author;
import com.example.bookshopsystem.repositories.AuthorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    @Override
    public Author getRandomAuthor() {
        long size = this.authorRepository.count();

        // 10 -> [0..9] -> [1..10]
        int authorId = new Random().nextInt((int) size) + 1;

        return this.authorRepository.findById(authorId).get();
    }

    @Override
    public List<Author> findDistinctByBooksReleaseDateBefore(LocalDate releaseDate) {
        return authorRepository.findDistinctByBooksReleaseDateBefore(releaseDate);
    }

    @Override
    public List<Author> findAllByFirstNameEndingWith(String ends) {
        return authorRepository.findAllByFirstNameEndingWith(ends);
    }

    @Override
    public List<Author> getTotalBookCopiesByAuthor() {
        return authorRepository.getTotalBookCopiesByAuthor();
    }
}
