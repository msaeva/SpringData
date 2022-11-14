package com.example.advancedqueriesapplication.repositories;

import com.example.advancedqueriesapplication.entities.Shampoo;
import com.example.advancedqueriesapplication.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;


public interface ShampooRepository extends JpaRepository<Shampoo, Long> {
    List<Shampoo> findByBrand(String brand);

    List<Shampoo> findAllBySizeOrderById(Size size);

    List<Shampoo> findAllByPriceAfterOrderByPriceDesc(BigDecimal price);

    @Query(value = "select s from Shampoo AS s " +
            "JOIN s.ingredients AS i " +
            "where i.name = :name")
    List<Shampoo> findByIngredient(@Param(value = "name") String ingredient);


    @Query(value = "select s from Shampoo AS s " +
            "JOIN s.ingredients AS i " +
            "where i.name  IN :ingredients")
    List<Shampoo> findByIngredients(List<String> ingredients);

    List<Shampoo> findAllBySizeOrLabelId(Size parsed, long labelId);

    int countShampooByPriceLessThan(BigDecimal price);

    @Query("select s from Shampoo as s where s.ingredients.size < :count")
    List<Shampoo> findByIngredientCountLessThan(int count);
}
