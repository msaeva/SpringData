package com.example.advancedqueriesapplication.repositories;

import com.example.advancedqueriesapplication.entities.Ingredient;
import org.hibernate.sql.Update;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    List<Ingredient> findByNameStartsWith(String name);
    List<Ingredient> findByNameInOrderByPrice(List<String> names);

    void deleteByName(String name);

    @Query("Update Ingredient as i set i.price = i.price * 1.10")
    @Modifying
    void updateAllPrice();
}
