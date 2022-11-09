package com.example.booshop.services.category;

import com.example.booshop.domain.entities.Category;

import java.util.List;
import java.util.Set;

public interface CategoryService {
    void seedCategories(List<Category> categories);

    boolean isDateSeeded();
    
    Set<Category> getRandomCategories();
}
