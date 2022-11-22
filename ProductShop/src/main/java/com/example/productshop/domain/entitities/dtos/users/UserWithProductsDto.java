package com.example.productshop.domain.entitities.dtos.users;

import com.example.productshop.domain.entitities.dtos.products.ProductsSoldWithCountDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserWithProductsDto {


    private String firstName;


    private String lastName;


    private Integer age;

    private ProductsSoldWithCountDto products;
}
