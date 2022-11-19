package com.example.softunigamestore.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    @ManyToOne(optional = false)
    private User user;

    @ManyToMany(targetEntity = Game.class, fetch = FetchType.EAGER)
    private Set<Game> games;

    private boolean isFinished;

    public Order(User user) {
        this.user = user;
        this.games = new HashSet<>();
        isFinished = false;
    }

    public Order() {

    }

    public void addGameToTheOrder(Game game) {
        this.games.add(game);
    }

    public void removeGameFromOrder(Game game) {
        this.games.remove(game);

    }
    public void removeAllGamesFromOrder() {
        this.games.clear();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }
}
