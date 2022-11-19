package com.example.softunigamestore;

import com.example.softunigamestore.services.GameService;
import com.example.softunigamestore.services.ShoppingCartService;
import com.example.softunigamestore.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static com.example.softunigamestore.constants.Commands.*;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private static final Scanner scanner = new Scanner(System.in);
    private final UserService userService;
    private final GameService gameService;
    private final ShoppingCartService shoppingCartService;

    public ConsoleRunner(UserService userService, GameService gameService, ShoppingCartService shoppingCartService) {
        this.userService = userService;
        this.gameService = gameService;
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public void run(String... args) throws Exception {

        String input = scanner.nextLine();
        while (!input.equals("close")) {
            final String[] arguments = input.split("\\|");

            String output = switch (arguments[0]) {
                case REGISTER_USER -> userService.registerUser(arguments);
                case LOGIN_USER -> userService.loginUser(arguments);
                case LOGOUT_USER -> userService.logoutUser();
                case ADD_GAME -> gameService.addGame(arguments);
                case DELETE_GAME -> gameService.deleteGame(arguments[1]);
                case EDIT_GAME -> gameService.editGame(arguments);
                case ALL_GAMES -> gameService.getAllGames();
                case DETAILED_GAME_INFO -> gameService.getDetailedInfo(arguments[1]);
                case ADD_ITEM -> shoppingCartService.addItem(arguments[1]);
                case REMOVE_ITEM -> shoppingCartService.removeItem(arguments[1]);
                case BUY_ITEM -> shoppingCartService.buyItem();
                default -> "No command found";

            };
            System.out.println(output);
            input = scanner.nextLine();
        }
    }
}
