package com.example.productshopxml.services;

import com.example.productshopxml.entitities.dtos.products.ProductDTO;
import com.example.productshopxml.entitities.dtos.products.ProductInRangeWithNoBuyerDto;
import com.example.productshopxml.entitities.dtos.products.wrappers.ProductsInRangeWithNoBuyerWrapperDto;
import com.example.productshopxml.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static com.example.productshopxml.constants.Paths.PRODUCTS_WITH_NO_BUYERS_IN_RANGE_XML_PATH;
import static com.example.productshopxml.constants.Paths.USERS_WITH_SOLD_PRODUCTS_XML_PATH;
import static com.example.productshopxml.constants.Utils.MODEL_MAPPER;
import static com.example.productshopxml.constants.Utils.writeXmlToFile;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public List<ProductInRangeWithNoBuyerDto> findAllByPriceBetweenAndAndBuyerIsNullOOrderByPrice(BigDecimal low, BigDecimal high) throws IOException, JAXBException {
        final List<ProductInRangeWithNoBuyerDto> products =
                this.productRepository.findAllByPriceBetweenAndBuyerIsNullOrderByPrice(low, high)
                        .orElseThrow(NoSuchElementException::new)
                        .stream()
                        .map(product -> MODEL_MAPPER.map(product, ProductDTO.class))
                        .map(ProductDTO::toProductInRangeWithNoBuyerDto)
                        .collect(Collectors.toList());

        final ProductsInRangeWithNoBuyerWrapperDto productsWrapper = new ProductsInRangeWithNoBuyerWrapperDto(products);


        writeXmlToFile(productsWrapper, PRODUCTS_WITH_NO_BUYERS_IN_RANGE_XML_PATH);
        return products;
    }
}
