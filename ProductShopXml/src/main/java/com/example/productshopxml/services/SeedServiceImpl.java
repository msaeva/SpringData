package com.example.productshopxml.services;

import com.example.productshopxml.entitities.Category;
import com.example.productshopxml.entitities.Product;
import com.example.productshopxml.entitities.User;
import com.example.productshopxml.entitities.dtos.categories.wrappers.CategoriesImportWrapperDto;
import com.example.productshopxml.entitities.dtos.products.wrappers.ProductsImportWrapperDto;
import com.example.productshopxml.entitities.dtos.users.UsersImportWrapperDto;
import com.example.productshopxml.repositories.CategoryRepository;
import com.example.productshopxml.repositories.ProductRepository;
import com.example.productshopxml.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.productshopxml.constants.Paths.*;
import static com.example.productshopxml.constants.Utils.MODEL_MAPPER;

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
    public void seedUsers() throws IOException, JAXBException {

        if (this.userRepository.count() == 0) {
            final FileReader fileReader = new FileReader(USER_XML_PATH.toFile());


            final JAXBContext context = JAXBContext.newInstance(UsersImportWrapperDto.class);
            final Unmarshaller unmarshaller = context.createUnmarshaller();
            final UsersImportWrapperDto usersDto = (UsersImportWrapperDto) unmarshaller.unmarshal(fileReader);

            List<User> users = usersDto.getUsers().stream()
                    .map(userDto -> MODEL_MAPPER.map(userDto, User.class))
                    .toList();


            userRepository.saveAllAndFlush(users);

            fileReader.close();
        }
    }

    @Override
    public void seedProducts() throws IOException, JAXBException {
        if (productRepository.count() == 0) {
            final FileReader fileReader = new FileReader(PRODUCTS_XML_PATH.toFile());

            final JAXBContext context = JAXBContext.newInstance(ProductsImportWrapperDto.class);
            final Unmarshaller unmarshaller = context.createUnmarshaller();
            ProductsImportWrapperDto productsDto = (ProductsImportWrapperDto) unmarshaller.unmarshal(fileReader);

            List<Product> products = productsDto.getProducts().stream()
                    .map(p -> MODEL_MAPPER.map(p, Product.class))
                    .map(this::setRandomSeller)
                    .map(this::setRandomBuyer)
                    .map(this::setRandomCategories)
                    .toList();

            productRepository.saveAllAndFlush(products);
            fileReader.close();
        }
    }

    @Override
    public void seedCategories() throws IOException, JAXBException {
        if (categoryRepository.count() == 0) {
            FileReader fileReader = new FileReader(CATEGORIES_XML_PATH.toFile());

            final JAXBContext context = JAXBContext.newInstance(CategoriesImportWrapperDto.class);

            final Unmarshaller unmarshaller = context.createUnmarshaller();

            final CategoriesImportWrapperDto categoriesDto = (CategoriesImportWrapperDto) unmarshaller.unmarshal(fileReader);


            List<Category> categories = categoriesDto.getCategories().stream()
                    .map(categoryImportDTO -> MODEL_MAPPER.map(categoryImportDTO, Category.class))
                    .toList();

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
