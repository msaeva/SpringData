package com.example.softunigamestore.services;

import com.example.softunigamestore.entities.User;

public interface UserService {
    String registerUser(String[] args);

    String loginUser(String[] args) ;

    String logoutUser();

    User getLoggedInUser();
}
