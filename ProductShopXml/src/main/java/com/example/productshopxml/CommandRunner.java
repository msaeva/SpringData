package com.example.productshopxml;

import com.example.productshopxml.services.CategoryService;
import com.example.productshopxml.services.ProductService;
import com.example.productshopxml.services.SeedService;
import com.example.productshopxml.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CommandRunner implements CommandLineRunner {

    private final SeedService seedService;
    private final ProductService productService;
    private final UserService userService;
    private final CategoryService categoryService;


    public CommandRunner(SeedService seedService, ProductService productService, UserService userService, CategoryService categoryService) {
        this.seedService = seedService;
        this.productService = productService;
        this.userService = userService;

        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
        productService.findAllByPriceBetweenAndAndBuyerIsNullOOrderByPrice
                (BigDecimal.valueOf(500), BigDecimal.valueOf(1000));

        //userService.findAllWithSoldProducts();
        // userService.findAllWithSoldProductsOrderByCount();

        // categoryService.getCategorySummary();


    }
}
