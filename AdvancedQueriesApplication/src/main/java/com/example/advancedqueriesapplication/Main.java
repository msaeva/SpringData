package com.example.advancedqueriesapplication;

import com.example.advancedqueriesapplication.entities.Shampoo;
import com.example.advancedqueriesapplication.services.IngredientService;
import com.example.advancedqueriesapplication.services.ShampooService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class Main implements CommandLineRunner {
    private final ShampooService shampooService;
    private final IngredientService ingredientService;

    public Main(ShampooService shampooService, IngredientService ingredientService) {
        this.shampooService = shampooService;
        this.ingredientService = ingredientService;
    }


    @Override
    public void run(String... args) throws Exception {
        updateIngredientPrice();
    }

    private void findShampoosByBrand() {
        for (Shampoo shampoo : shampooService.findByBrand("Silk Comb")) {
            System.out.println(shampoo.getId());
        }
    }

    private void selectShampoosBySize() {
        String size = new Scanner(System.in).nextLine().toLowerCase();

        this.shampooService.findAllBySizeOrderById(size).forEach(System.out::println);
    }

    private void selectShampoosByPrice() {
        BigDecimal price = new Scanner(System.in).nextBigDecimal();

        this.shampooService.findAllByPriceAfterOrderByPriceDesc(price).forEach(s -> System.out.println(s.getBrand() + " " + s.getPrice()));
    }

    private void findShampooByIngredient() {
        this.shampooService.findByIngredient("Berry").forEach(System.out::println);
    }

    private void findShampooByIngredients() {
        String nextLine = new Scanner(System.in).nextLine();

        List<String> ingredients = new ArrayList<>();

        while (!nextLine.isBlank()) {
            ingredients.add(nextLine);

            nextLine = new Scanner(System.in).nextLine();
        }
        this.shampooService.findByIngredients(ingredients).forEach(System.out::println);
    }

    private void selectShampoosBySizeOrLabel() {
        String size = new Scanner(System.in).nextLine().toUpperCase();

        long labelId = new Scanner(System.in).nextLong();

        this.shampooService.findBySizeOrLabelId(size, labelId).forEach(System.out::println);
    }

    private void selectIngredientByName() {
        String name = new Scanner(System.in).nextLine();

        this.ingredientService.selectIngredientByName(name).forEach(System.out::println);
    }

    private void selectIngredientsNames() {
        String nextLine = new Scanner(System.in).nextLine();

        List<String> ingredients = new ArrayList<>();

        while (!nextLine.isBlank()) {
            ingredients.add(nextLine);

            nextLine = new Scanner(System.in).nextLine();
        }
        this.ingredientService.selectIngredientsNamesContained(ingredients);
    }

    private void countShampooByPrice() {
        String price = new Scanner(System.in).nextLine();

        long count = this.shampooService.countShampoosWithLowerPrice(price);
        System.out.println(count);
    }

    private void selectShampoosByIngredientCount() {
        int count = new Scanner(System.in).nextInt();

        this.shampooService.findWithIngredientsCountLessThan(count).forEach(System.out::println);
    }

    private void deleteIngredientsByName() {
        this.ingredientService.deleteByName("Apple");
    }
    private void updateIngredientPrice() {
        this.ingredientService.updatePrice();
    }
}

