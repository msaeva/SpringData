package com.example.productshopxml.services;

import com.example.productshopxml.entitities.dtos.categories.CategoryProductSummaryDto;
import com.example.productshopxml.entitities.dtos.categories.wrappers.CategoriesProductSummaryWrapperDto;
import com.example.productshopxml.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import static com.example.productshopxml.constants.Paths.CATEGORIES_BY_PRODUCTS_XML_PATH;
import static com.example.productshopxml.constants.Utils.writeXmlToFile;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryProductSummaryDto> getCategorySummary() throws IOException, JAXBException {
        final List<CategoryProductSummaryDto> categories = this.categoryRepository
                .getCategorySummary()
                .orElseThrow(NoSuchElementException::new);

        CategoriesProductSummaryWrapperDto wrapperDto = new CategoriesProductSummaryWrapperDto(categories);

        writeXmlToFile(wrapperDto, CATEGORIES_BY_PRODUCTS_XML_PATH);

        return categories;
    }
}
