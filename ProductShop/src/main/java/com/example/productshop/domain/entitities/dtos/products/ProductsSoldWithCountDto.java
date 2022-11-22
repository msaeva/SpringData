package com.example.productshop.domain.entitities.dtos.products;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProductsSoldWithCountDto {
    private Integer count;
    private List<ProductBasicInfo> products;

    public ProductsSoldWithCountDto(List<ProductBasicInfo> products) {
        this.products = products;
        this.count = products.size();
    }
}
