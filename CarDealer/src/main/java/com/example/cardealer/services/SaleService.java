package com.example.cardealer.services;


import com.example.cardealer.domain.entities.dtos.sale.SaleWithDiscountDto;

import java.io.IOException;
import java.util.List;

public interface SaleService {
    List<SaleWithDiscountDto> getAllSales() throws IOException;

}
