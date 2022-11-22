package com.example.productshop.services;

import com.example.productshop.domain.entitities.dtos.products.ProductInRangeWithNoBuyerDto;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
   List<ProductInRangeWithNoBuyerDto> findAllByPriceBetweenAndAndBuyerIsNullOOrderByPrice(BigDecimal low, BigDecimal high) throws IOException;
}
