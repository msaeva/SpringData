package com.example.advancedqueriesapplication.services;

import com.example.advancedqueriesapplication.entities.Ingredient;

import java.util.List;

public interface IngredientService {
    List<Ingredient> selectIngredientByName(String name);
    void selectIngredientsNamesContained(List<String> names);
    void deleteByName(String name);

    void updatePrice();
}
