package com.example.productshop;

import com.example.productshop.services.CategoryService;
import com.example.productshop.services.ProductService;
import com.example.productshop.services.SeedService;
import com.example.productshop.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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

        //  productService.findAllByPriceBetweenAndAndBuyerIsNullOOrderByPrice
        // (BigDecimal.valueOf(500), BigDecimal.valueOf(1000));

          userService.findAllBySellingProductsBuyerIsNotNullOrderBySellingProductsBuyerFirstName();

       // categoryService.getCategorySummary();


    }
}
