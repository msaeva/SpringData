package com.example.bookshopsystem.repositories;

import com.example.bookshopsystem.entities.AgeRestriction;
import com.example.bookshopsystem.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByReleaseDateAfter(LocalDate releaseDate);

    int countByReleaseDateAfter(LocalDate releaseDate);

    //@Query("select b from Book as b where b.ageRestriction = :ageRestriction")
    List<Book> findBooksByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findAllByCopiesLessThan(int copies);

    @Query(value = "select b FROM Book as b where b.price < :lessThan OR b.price > :greaterThan")
    List<Book> findAllByPriceGreaterThanAndLessThan(BigDecimal lessThan, BigDecimal greaterThan);

    List<Book> findAllByReleaseDateNot(LocalDate releaseDate);

    List<Book> findAllByReleaseDateBefore(LocalDate releaseDate);

    List<Book> findAllByTitleContainingIgnoreCase(String substring);

    List<Book> findAllByAuthorLastNameStartingWith(String startString);


    @Query(value = "select count(b) from Book as b" +
            " where length(b.title) > :number")
    int findByTitleCountGreaterThan(int number);



}
