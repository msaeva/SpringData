package com.example.productshopxml.services;

import com.example.productshopxml.entitities.User;
import com.example.productshopxml.entitities.dtos.products.wrappers.ProductsInRangeWithNoBuyerWrapperDto;
import com.example.productshopxml.entitities.dtos.users.UserDTO;
import com.example.productshopxml.entitities.dtos.users.UserWithProductsDto;
import com.example.productshopxml.entitities.dtos.users.UsersImportWrapperDto;
import com.example.productshopxml.entitities.dtos.users.UsersWithSoldProductsDto;
import com.example.productshopxml.entitities.dtos.users.wrappers.UserWithSoldProductsWrapperDto;
import com.example.productshopxml.entitities.dtos.users.wrappers.UsersWithProductsWrapperDto;
import com.example.productshopxml.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.example.productshopxml.constants.Paths.*;
import static com.example.productshopxml.constants.Utils.MODEL_MAPPER;
import static com.example.productshopxml.constants.Utils.writeXmlToFile;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<UsersWithSoldProductsDto> findAllWithSoldProducts() throws IOException, JAXBException {

        final List<UsersWithSoldProductsDto> usersWithSoldProductsDtoList =
                this.userRepository.findAllWithSoldProducts()
                        .stream()
                        .map(user -> MODEL_MAPPER.map(user, UsersWithSoldProductsDto.class))
                        .toList();

        final UserWithSoldProductsWrapperDto userWithSoldProductsWrapperDto =
                new UserWithSoldProductsWrapperDto(usersWithSoldProductsDtoList);

        writeXmlToFile(userWithSoldProductsWrapperDto, USERS_WITH_SOLD_PRODUCTS_XML_PATH);

        return usersWithSoldProductsDtoList;
    }

    @Override
    public UsersWithProductsWrapperDto findAllWithSoldProductsOrderByCount() throws JAXBException {
        List<User> users = userRepository.findAllWithSoldProductsOrderByCount();

        List<UserWithProductsDto> userWithProductsDtos = users
                .stream()
                .map(user -> MODEL_MAPPER.map(user, UserDTO.class))
                .map(UserDTO::toUserWithProductsDto)
                .collect(Collectors.toList());

        UsersWithProductsWrapperDto wrapperDto = new UsersWithProductsWrapperDto(userWithProductsDtos);

        writeXmlToFile(wrapperDto, USERS_AND_PRODUCTS_XML_PATH);

        return wrapperDto;
    }
}
