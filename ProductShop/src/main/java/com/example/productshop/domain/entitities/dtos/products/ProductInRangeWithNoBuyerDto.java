package com.example.productshop.domain.entitities.dtos.products;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductInRangeWithNoBuyerDto {

    private String name;


    private BigDecimal price;


    private String seller;
}
