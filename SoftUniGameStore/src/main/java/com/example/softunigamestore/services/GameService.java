package com.example.softunigamestore.services;

public interface GameService {
    String addGame(String[] arguments);

    String deleteGame(String id);

    String editGame(String[] arguments);

    String getAllGames();

    String getDetailedInfo(String title);
}
