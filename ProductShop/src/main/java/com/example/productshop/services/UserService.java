package com.example.productshop.services;

import com.example.productshop.domain.entitities.dtos.users.UsersWithSoldProductsDto;

import java.io.IOException;
import java.util.List;

public interface UserService {
    List<UsersWithSoldProductsDto> findAllBySellingProductsBuyerIsNotNullOrderBySellingProductsBuyerFirstName() throws IOException, IOException;
}
