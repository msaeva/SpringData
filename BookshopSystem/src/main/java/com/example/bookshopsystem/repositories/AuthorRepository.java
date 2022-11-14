package com.example.bookshopsystem.repositories;

import com.example.bookshopsystem.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    List<Author> findDistinctByBooksReleaseDateBefore(LocalDate releaseDate);

    List<Author> findAllByFirstNameEndingWith(String ends);


    @Query("select count(b.copies) as total_count from  Book as b " +
            "group by b.author.id " +
            "order by total_count desc")
    List<Author> getTotalBookCopiesByAuthor();
}
