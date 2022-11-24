package com.example.productshopxml.entitities.dtos.users;

import com.example.productshopxml.entitities.dtos.products.ProductSoldDto;
import com.example.productshopxml.entitities.dtos.products.wrappers.ProductsSoldWrapperDto;
import com.example.productshopxml.entitities.dtos.users.wrappers.UserWithSoldProductsXmlDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersWithSoldProductsDto {
    @XmlAttribute(name = "first-name")
    private String firstName;

    @XmlAttribute(name = "last-name")
    private String lastName;

//    @XmlElement(name = "sold-products")
//    private List<ProductSoldDto> boughtProducts;

    @XmlElement(name = "sold-products")
    private ProductsSoldWrapperDto boughtProducts;


//    public static List<UserWithSoldProductsXmlDto> toUsersWithSoldProductsDto(List<UsersWithSoldProductsDto> input) {
//        return input.stream()
//                .map(user -> new UserWithSoldProductsXmlDto(
//                        user.getFirstName(),
//                        user.getLastName(),
//                        new ProductsSoldWrapperDto(user.getBoughtProducts())))
//                .toList();
//    }
}