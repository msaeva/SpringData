package com.example.productshop.services;

import com.example.productshop.domain.entitities.dtos.products.ProductDTO;
import com.example.productshop.domain.entitities.dtos.products.ProductInRangeWithNoBuyerDto;
import com.example.productshop.repositories.ProductRepository;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static com.example.productshop.constants.Paths.PRODUCTS_WITH_NO_BUYERS_IN_RANGE_JSON_PATH;
import static com.example.productshop.constants.Utils.MODEL_MAPPER;
import static com.example.productshop.constants.Utils.writeJsonIntoFile;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    @Transactional
    public List<ProductInRangeWithNoBuyerDto> findAllByPriceBetweenAndAndBuyerIsNullOOrderByPrice(BigDecimal low, BigDecimal high) throws IOException {
        final List<ProductInRangeWithNoBuyerDto> products =
                this.productRepository.findAllByPriceBetweenAndBuyerIsNullOrderByPrice(low, high)
                        .orElseThrow(NoSuchElementException::new)
                        .stream()
                        .map(product -> MODEL_MAPPER.map(product, ProductDTO.class))
                        .map(ProductDTO::toProductInRangeWithNoBuyerDto)
                        .collect(Collectors.toList());

        writeJsonIntoFile(products, PRODUCTS_WITH_NO_BUYERS_IN_RANGE_JSON_PATH);

        return products;
    }
}
