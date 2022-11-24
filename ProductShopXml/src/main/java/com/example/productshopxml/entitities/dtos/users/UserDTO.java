package com.example.productshopxml.entitities.dtos.users;

import com.example.productshopxml.entitities.dtos.products.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

import static com.example.productshopxml.entitities.dtos.products.ProductDTO.toProductsSoldWithCountDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String firstName;

    private String lastName;

    private Integer age;

    private Set<ProductDTO> sellingProducts;

    private Set<ProductDTO> boughtProducts;

    private Set<UserDTO> friends;

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public UserWithProductsDto toUserWithProductsDto() {
        return new UserWithProductsDto(firstName, lastName, age, toProductsSoldWithCountDto(sellingProducts));
    }
}
