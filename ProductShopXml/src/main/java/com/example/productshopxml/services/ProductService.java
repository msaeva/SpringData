package com.example.productshopxml.services;

import com.example.productshopxml.entitities.dtos.products.ProductInRangeWithNoBuyerDto;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
   List<ProductInRangeWithNoBuyerDto> findAllByPriceBetweenAndAndBuyerIsNullOOrderByPrice(BigDecimal low, BigDecimal high) throws IOException, JAXBException;
}
