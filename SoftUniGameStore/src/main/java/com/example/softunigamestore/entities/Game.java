package com.example.softunigamestore.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "games")
public class Game extends BaseEntity {

    @Column(nullable = false)
    private String title;

    @Column(name = "trailer_id")
    private String trailerId;

    @Column(name = "image_url")
    private String imageUrl;

    @Column
    private float size;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private String description;

    @Column(name = "release_Date", nullable = false)
    private LocalDate releaseDate;

    public Game() {
    }

    public Game(String title, String trailerId, String imageThumbnail,
                float size, BigDecimal price, String description, LocalDate releaseDate) {
        this.title = title;
        this.trailerId = trailerId;
        this.imageUrl = imageThumbnail;
        this.size = size;
        this.price = price;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTrailerId() {
        return trailerId;
    }

    public void setTrailerId(String trailerId) {
        this.trailerId = trailerId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageThumbnail) {
        this.imageUrl = imageThumbnail;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTitleAndPrice(){
        return this.title + " " + this.price;
    }

    public String getDetailedInfo() {
        return "Title: " + this.title + System.lineSeparator() +
                "Price: " + this.price + System.lineSeparator() +
                "Description: " + this.description + System.lineSeparator() +
                "Release date: " + this.releaseDate;
    }
}

