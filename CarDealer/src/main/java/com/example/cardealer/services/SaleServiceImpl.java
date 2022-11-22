package com.example.cardealer.services;

import com.example.cardealer.domain.entities.Sale;
import com.example.cardealer.domain.entities.dtos.sale.SaleDto;
import com.example.cardealer.domain.entities.dtos.sale.SaleWithDiscountDto;
import com.example.cardealer.repositories.SaleRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.cardealer.domain.entities.constants.Utils.MODEL_MAPPER;
import static com.example.cardealer.domain.entities.constants.Utils.writeJsonIntoFile;

@Service
public class SaleServiceImpl implements SaleService {
    private final SaleRepository saleRepository;

    public SaleServiceImpl(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Override
    public List<SaleWithDiscountDto> getAllSales() throws IOException {
        List<Sale> sales = saleRepository.findAll();

        List<SaleWithDiscountDto> saleWithDiscountDtos = sales.stream().map(sale -> MODEL_MAPPER
                        .map(sale, SaleDto.class))
                .map(SaleDto::saleWithDiscountDto)
                .collect(Collectors.toList());


        writeJsonIntoFile(saleWithDiscountDtos, Path.of("src/main/resources/output/sales-discounts.json"));
        
        return saleWithDiscountDtos;
    }
}
