package com.example.productshop.repositories;

import com.example.productshop.domain.entitities.Category;
import com.example.productshop.domain.entitities.dtos.categories.CategoryProductSummaryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query(value = "select * from `product-shop`.categories order by RAND () LIMIT 1",
            nativeQuery = true)
    Optional<Category> getRandomEntity();

    @Query("select new com.example.productshop.domain.entitities.dtos.categories.CategoryProductSummaryDto" +
            "(c.name, count(p.id), avg(p.price), sum(p.price)) " +
            "from Product p " +
            "join p.categories c " +
            "group by c.id " +
            "order by count(p.id)")
    Optional<List<CategoryProductSummaryDto>> getCategorySummary();
}
