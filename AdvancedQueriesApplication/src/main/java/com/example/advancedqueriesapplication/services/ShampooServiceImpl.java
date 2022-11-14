package com.example.advancedqueriesapplication.services;

import com.example.advancedqueriesapplication.entities.Shampoo;
import com.example.advancedqueriesapplication.entities.Size;
import com.example.advancedqueriesapplication.repositories.ShampooRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ShampooServiceImpl implements ShampooService {
    private final ShampooRepository shampooRepository;

    public ShampooServiceImpl(ShampooRepository shampooRepository) {
        this.shampooRepository = shampooRepository;
    }

    @Override
    public List<Shampoo> findByBrand(String brand) {
        return this.shampooRepository.findByBrand(brand);
    }

    @Override
    public List<Shampoo> findAllBySizeOrderById(String size) {
        Size parsedSize = Size.valueOf(size);

        return this.shampooRepository.findAllBySizeOrderById(parsedSize);
    }

    @Override
    public List<Shampoo> findAllByPriceAfterOrderByPriceDesc(BigDecimal price) {
        return this.shampooRepository.findAllByPriceAfterOrderByPriceDesc(price);
    }

    @Override
    public List<Shampoo> findByIngredient(String ingredient) {
        return this.shampooRepository.findByIngredient(ingredient);
    }

    @Override
    public List<Shampoo> findByIngredients(List<String> ingredients) {
        return this.shampooRepository.findByIngredients(ingredients);
    }

    @Override
    public List<Shampoo> findBySizeOrLabelId(String size, long labelId) {
        Size parsed = Size.valueOf(size);
        return this.shampooRepository.findAllBySizeOrLabelId(parsed, labelId);
    }

    @Override
    public long countShampoosWithLowerPrice(String price) {
        BigDecimal parsed = new BigDecimal(price);
        return this.shampooRepository.countShampooByPriceLessThan(parsed);
    }

    @Override
    public List<Shampoo> findWithIngredientsCountLessThan(int count) {
        return shampooRepository.findByIngredientCountLessThan(count);
    }
}
