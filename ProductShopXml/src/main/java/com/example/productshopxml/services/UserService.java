package com.example.productshopxml.services;

import com.example.productshopxml.entitities.dtos.users.UsersWithSoldProductsDto;
import com.example.productshopxml.entitities.dtos.users.wrappers.UsersWithProductsWrapperDto;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public interface UserService {
    List<UsersWithSoldProductsDto> findAllWithSoldProducts() throws IOException, IOException, JAXBException;
    UsersWithProductsWrapperDto findAllWithSoldProductsOrderByCount() throws JAXBException;
}
