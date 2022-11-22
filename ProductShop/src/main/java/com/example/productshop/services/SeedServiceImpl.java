package com.example.productshop.services;

import com.example.productshop.domain.entitities.Category;
import com.example.productshop.domain.entitities.Product;
import com.example.productshop.domain.entitities.User;
import com.example.productshop.domain.entitities.dtos.categories.CategoryImportDTO;
import com.example.productshop.domain.entitities.dtos.products.ProductImportDTO;
import com.example.productshop.domain.entitities.dtos.users.UserImportDTO;
import com.example.productshop.repositories.CategoryRepository;
import com.example.productshop.repositories.ProductRepository;
import com.example.productshop.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.productshop.constants.Paths.*;
import static com.example.productshop.constants.Utils.GSON;
import static com.example.productshop.constants.Utils.MODEL_MAPPER;

@Service
public class SeedServiceImpl implements SeedService {
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    private final ProductRepository productRepository;

    public SeedServiceImpl(UserRepository userRepository, CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;

        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void seedUsers() throws IOException {

        if (this.userRepository.count() == 0) {
            final FileReader fileReader = new FileReader(USER_JSON_PATH.toFile());


            UserImportDTO[] userImportDTOS = GSON.fromJson(fileReader, UserImportDTO[].class);
            List<User> users = Arrays.stream(userImportDTOS)
                    .map(userImportDTO -> MODEL_MAPPER.map(userImportDTO, User.class))
                    .collect(Collectors.toList());

            userRepository.saveAllAndFlush(users);

            fileReader.close();
        }
    }

    @Override
    public void seedProducts() throws IOException {
        if (productRepository.count() == 0) {
            final FileReader fileReader = new FileReader(PRODUCTS_JSON_PATH.toFile());

            ProductImportDTO[] productImportDTOS = GSON.fromJson(fileReader, ProductImportDTO[].class);

            final List<Product> products = Arrays.stream(productImportDTOS)
                    .map(productImportDTO -> MODEL_MAPPER.map(productImportDTO, Product.class))
                    .map(this::setRandomSeller)
                    .map(this::setRandomBuyer)
                    .map(this::setRandomCategories)
                    .collect(Collectors.toList());


            productRepository.saveAllAndFlush(products);
            fileReader.close();
        }
    }

    @Override
    public void seedCategories() throws IOException {
        if (categoryRepository.count() == 0) {
            FileReader fileReader = new FileReader(CATEGORIES_JSON_PATH.toFile());

            CategoryImportDTO[] categoryImportDTOS = GSON.fromJson(fileReader, CategoryImportDTO[].class);

            List<Category> categories = Arrays.stream(categoryImportDTOS)
                    .map(categoryImportDTO -> MODEL_MAPPER.map(categoryImportDTO, Category.class))
                    .collect(Collectors.toList());

            categoryRepository.saveAllAndFlush(categories);
            fileReader.close();
        }

    }

    private Product setRandomCategories(Product product) {
        final Random random = new Random();

        final int numberOfCategories = random.nextInt((int) this.categoryRepository.count());

        Set<Category> categories = new HashSet<>();

        for (int i = 0; i < numberOfCategories; i++) {
            Category category = this.categoryRepository.getRandomEntity()
                    .orElseThrow(NoSuchElementException::new);

            categories.add(category);
        }

        product.setCategories(categories);

        return product;
    }

    private Product setRandomBuyer(Product product) {
        if (product.getPrice().compareTo(BigDecimal.valueOf(700L)) > 0) {
            User buyer = this.userRepository.getRandomEntity().orElseThrow(NoSuchElementException::new);

            while (buyer.equals(product.getSeller())) {
                buyer = this.userRepository.getRandomEntity().orElseThrow(NoSuchElementException::new);
            }
            product.setBuyer(buyer);
        }

        return product;
    }

    private Product setRandomSeller(Product product) {
        final User seller = this.userRepository.getRandomEntity().orElseThrow(NoSuchElementException::new);

        product.setSeller(seller);

        return product;
    }
}
