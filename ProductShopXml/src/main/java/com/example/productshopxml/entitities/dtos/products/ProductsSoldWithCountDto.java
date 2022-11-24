package com.example.productshopxml.entitities.dtos.products;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "sold-products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductsSoldWithCountDto {
    @XmlAttribute
    private Integer count;
    @XmlElement(name = "product")
    private List<ProductBasicInfo> products;

    public ProductsSoldWithCountDto(List<ProductBasicInfo> products) {
        this.products = products;
        this.count = products.size();
    }
}
