package com.example.productshop.services;

import com.example.productshop.domain.entitities.dtos.categories.CategoryProductSummaryDto;

import java.io.IOException;
import java.util.List;

public interface CategoryService {
    List<CategoryProductSummaryDto> getCategorySummary() throws IOException;
}
