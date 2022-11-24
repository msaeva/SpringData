package com.example.productshopxml.entitities.dtos.products;

import com.example.productshopxml.entitities.dtos.categories.CategoryDto;
import com.example.productshopxml.entitities.dtos.users.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private String name;

    private BigDecimal price;

    private UserDTO buyer;

    private UserDTO seller;

    private Set<CategoryDto> categories;

    public ProductInRangeWithNoBuyerDto toProductInRangeWithNoBuyerDto() {
        return new ProductInRangeWithNoBuyerDto(name, price, seller.getFullName());
    }

    public static ProductsSoldWithCountDto toProductsSoldWithCountDto(Set<ProductDTO> sellingProducts) {
        return new ProductsSoldWithCountDto(sellingProducts
                .stream()
                .map(ProductDTO::toProductBasicInfo)
                .collect(Collectors.toList()));
    }

    public static ProductBasicInfo toProductBasicInfo(ProductDTO productDto) {
        return new ProductBasicInfo(productDto.getName(), productDto.getPrice());
    }
}
