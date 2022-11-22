package com.example.productshop.services;

import com.example.productshop.domain.entitities.dtos.categories.CategoryProductSummaryDto;
import com.example.productshop.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import static com.example.productshop.constants.Paths.CATEGORIES_BY_PRODUCTS_JSON_PATH;
import static com.example.productshop.constants.Utils.writeJsonIntoFile;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryProductSummaryDto> getCategorySummary() throws IOException {
        final List<CategoryProductSummaryDto> categories = this.categoryRepository
                .getCategorySummary()
                .orElseThrow(NoSuchElementException::new);

        writeJsonIntoFile(categories, CATEGORIES_BY_PRODUCTS_JSON_PATH);
        return categories;
    }
}
