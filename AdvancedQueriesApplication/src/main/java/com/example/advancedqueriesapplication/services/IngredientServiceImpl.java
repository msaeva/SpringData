package com.example.advancedqueriesapplication.services;

import com.example.advancedqueriesapplication.entities.Ingredient;
import com.example.advancedqueriesapplication.repositories.IngredientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public List<Ingredient> selectIngredientByName(String name) {
        return ingredientRepository.findByNameStartsWith(name);
    }

    @Override
    public void selectIngredientsNamesContained(List<String> names) {
        this.ingredientRepository.findByNameInOrderByPrice(names);
    }

    @Transactional
    @Override
    public void deleteByName(String name) {
        ingredientRepository.deleteByName(name);
    }

    @Transactional
    @Override
    public void updatePrice() {
        this.ingredientRepository.updateAllPrice();
    }
}
