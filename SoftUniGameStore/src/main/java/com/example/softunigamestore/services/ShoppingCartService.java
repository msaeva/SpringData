package com.example.softunigamestore.services;

public interface ShoppingCartService {
    String addItem(String argument);

    String removeItem(String argument);

    String buyItem();
}
