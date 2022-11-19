package com.example.softunigamestore.services;

import com.example.softunigamestore.entities.Game;
import com.example.softunigamestore.entities.dtos.GameDTO;
import com.example.softunigamestore.repositories.GameRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.softunigamestore.constants.Commands.*;

@Service
public class GameServiceImpl implements GameService {
    private final ModelMapper modelMapper = new ModelMapper();

    private final GameRepository gameRepository;
    private final UserService userService;

    public GameServiceImpl(GameRepository gameRepository, UserService userService) {
        this.gameRepository = gameRepository;
        this.userService = userService;
    }

    @Override
    public String addGame(String[] args) {

        if (this.userService.getLoggedInUser() != null && this.userService.getLoggedInUser().getAdmin()) {
            final String title = args[1];

            final BigDecimal price = new BigDecimal(args[2]);

            final float size = Float.parseFloat(args[3]);

            final String trailer = args[4];

            final String imageUrl = args[5];

            final String description = args[6];

            final LocalDate releaseDate = LocalDate.now();

            final GameDTO gameDTO = new GameDTO(title, trailer, imageUrl, size, price, description, releaseDate);

            final Game gameToSave = gameDTO.toGame();

            // Game gameToSave2 = modelMapper.map(gameDTO, Game.class);

            this.gameRepository.save(gameToSave);

            return "Added " + title;
        }
        return "No login user";
    }

    @Override
    public String deleteGame(String id) {
        if (userService.getLoggedInUser() == null || !userService.getLoggedInUser().getAdmin()) {
            return "No login user or admin";
        }

        Long gameIdToDelete = Long.parseLong(id);

        Optional<Game> gameToDelete = gameRepository.findById(gameIdToDelete);

        if (gameToDelete.isPresent()) {
            gameRepository.delete(gameToDelete.get());

            return "Deleted " + gameToDelete.get().getTitle();
        }
        return "Cannot delete game with id " + id;
    }

    @Override
    public String editGame(String[] args) {
        if (userService.getLoggedInUser() == null || !userService.getLoggedInUser().getAdmin()) {
            return "No login user or admin";
        }
        Long gameIdToDelete = Long.parseLong(args[1]);

        Optional<Game> existGame = gameRepository.findById(gameIdToDelete);

        if (existGame.isEmpty()) {
            return "Cannot edit game wit id " + gameIdToDelete;
        }
        for (int i = 2; i < args.length; i++) {
            String[] data = args[i].split("=");
            String field = data[0];

            switch (field) {
                case EDIT_PRICE:
                    BigDecimal newPrice = new BigDecimal(data[1]);
                    existGame.get().setPrice(newPrice);
                    break;
                case EDIT_TITLE:
                    existGame.get().setTitle(data[1]);
                    break;
                case EDIT_TRAILER_ID:
                    existGame.get().setTrailerId(data[1]);
                    break;
                case EDIT_IMAGE_URL:
                    existGame.get().setImageUrl(data[1]);
                    break;
                case EDIT_SIZE:
                    float newSize = Float.parseFloat(data[1]);
                    existGame.get().setSize(newSize);
                    break;
                case EDIT_DESCRIPTION:
                    existGame.get().setDescription(args[1]);
                    break;
                case EDIT_RELEASE_DATE:
                    break;
            }

        }

        gameRepository.save(existGame.get());

        return "Edited " + existGame.get().getTitle();
    }

    @Override
    public String getAllGames() {
        List<Game> games = gameRepository.findAll();

        if (games.isEmpty()) {
            return "No games";
        }
        return games.stream().map(Game::getTitleAndPrice)
                .collect(Collectors.joining(System.lineSeparator()));

    }

    @Override
    public String getDetailedInfo(String title) {
        Game game = gameRepository.findByTitle(title);

        if (game == null) {
            return "No game with this title";
        }
        return game.getDetailedInfo();
    }
}
