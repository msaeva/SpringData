package com.example.softunigamestore.services;

import com.example.softunigamestore.entities.Game;
import com.example.softunigamestore.entities.Order;
import com.example.softunigamestore.entities.User;
import com.example.softunigamestore.repositories.GameRepository;
import com.example.softunigamestore.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final UserService userService;

    private final GameRepository gameRepository;
    private final OrderRepository orderRepository;

    public ShoppingCartServiceImpl(UserService userService, GameRepository gameRepository, OrderRepository orderRepository) {
        this.userService = userService;
        this.gameRepository = gameRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public String addItem(String title) {
        User user = userService.getLoggedInUser();
        if (user == null) {
            return "Login in your profile";
        }

        Game game = gameRepository.findByTitle(title);
        if (game == null) {
            return "No game with that title " + title;
        }

        boolean isUserHasThatGame = user.getGames().stream().anyMatch(g -> g == game);
        if (isUserHasThatGame) {
            return "Already bought this game " + game.getTitle();
        }

        Order order = new Order(user);
        order.addGameToTheOrder(game);

        this.orderRepository.save(order);

        return game.getTitle() + " added to cart.";
    }

    @Override
    public String removeItem(String title) {
        Game game = gameRepository.findByTitle(title);
        if (game == null) {
            return "No game with that title " + title;
        }
        List<Order> allOrders = orderRepository.findAll();

        Optional<Order> order = allOrders.stream().filter(o -> !o.isFinished()).findFirst();

        if (order.isPresent()) {
            order.get().removeGameFromOrder(game);
        }

        return game.getTitle() + " removed form cart.";
    }

    @Override
    public String buyItem() {
        User user = userService.getLoggedInUser();

        List<Order> allOrders = orderRepository.findAll();

        Optional<Order> order = allOrders.stream().filter(o -> !o.isFinished()).findFirst();

        if (order.isEmpty()) {
            return "Add items to your cart.";
        }
        Set<Game> orderGames = order.get().getGames();

        for (Game game : orderGames) {
            user.buyGame(game);
        }

        order.get().setFinished(true);

        String collect = orderGames.stream().map(Game::getTitle)
                .collect(Collectors.joining("-" + System.lineSeparator()));

        order.get().removeAllGamesFromOrder();

        return "Successfully bought games: " + System.lineSeparator() +
                collect;
    }
}
