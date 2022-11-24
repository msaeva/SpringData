package com.example.productshopxml.entitities.dtos.categories;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryProductSummaryDto {

    @XmlAttribute(name = "name")
    private String category;

    @XmlElement(name = "products-count")
    private Long productsCount;

    @XmlElement(name = "average-price")
    private Double averagePrice;

    @XmlElement(name = "total-revenue")
    private BigDecimal totalRevenue;
}
