package com.example.productshop.domain.entitities.dtos.categories;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryProductSummaryDto {

    private String category;

    private Long productsCount;

    private Double averagePrice;

    private BigDecimal totalRevenue;
}
