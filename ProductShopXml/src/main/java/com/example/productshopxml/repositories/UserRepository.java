package com.example.productshopxml.repositories;

import com.example.productshopxml.entitities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select * from `product-shop-xml`.users order by RAND () LIMIT 1",
            nativeQuery = true)
    Optional<User> getRandomEntity();

    @Query("SELECT u FROM User u" +
            " WHERE " +
            "   (SELECT COUNT(p) " +
            "    FROM Product p " +
            "    WHERE p.seller = u AND p.buyer IS NOT NULL) > 0 ")
    List<User> findAllWithSoldProductsOrderByCount();

    @Query("SELECT u FROM User u" +
            " WHERE " +
            "   (SELECT COUNT(p) " +
            "    FROM Product p " +
            "    WHERE p.seller = u AND p.buyer IS NOT NULL) > 0" +
            " ORDER BY u.lastName, u.firstName")
    List<User> findAllWithSoldProducts();

}
