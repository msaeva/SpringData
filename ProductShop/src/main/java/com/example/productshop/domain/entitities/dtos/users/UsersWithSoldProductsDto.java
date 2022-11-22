package com.example.productshop.domain.entitities.dtos.users;

import com.example.productshop.domain.entitities.dtos.products.ProductSoldDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsersWithSoldProductsDto {
    private String firstName;

    private String lastName;

    private List<ProductSoldDto> boughtProducts;

}