package com.example.advancedqueriesapplication.services;

import com.example.advancedqueriesapplication.entities.Shampoo;

import java.math.BigDecimal;
import java.util.List;

public interface ShampooService {
    List<Shampoo> findByBrand(String brand);

    List<Shampoo> findAllBySizeOrderById(String size);

    List<Shampoo> findAllByPriceAfterOrderByPriceDesc(BigDecimal price);

    List<Shampoo> findByIngredient(String ingredient);

    List<Shampoo> findByIngredients(List<String> ingredients);

    List<Shampoo> findBySizeOrLabelId(String size, long labelId);

    long countShampoosWithLowerPrice(String price);

    List<Shampoo> findWithIngredientsCountLessThan(int count);

}

