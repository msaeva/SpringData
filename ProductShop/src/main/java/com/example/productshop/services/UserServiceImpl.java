package com.example.productshop.services;

import com.example.productshop.domain.entitities.dtos.users.UsersWithSoldProductsDto;
import com.example.productshop.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static com.example.productshop.constants.Paths.USERS_WITH_SOLD_PRODUCTS_JSON_PATH;
import static com.example.productshop.constants.Utils.MODEL_MAPPER;
import static com.example.productshop.constants.Utils.writeJsonIntoFile;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<UsersWithSoldProductsDto> findAllBySellingProductsBuyerIsNotNullOrderBySellingProductsBuyerFirstName() throws IOException {
//        Optional<List<User>> users =
//                userRepository.findAllBySellingProductsBuyerIsNotNullOrderBySellingProductsBuyerFirstName();
//
//
//        Stream<UsersWithSoldProductsDto> usersWithSoldProductsDtoStream =
//                users.get().stream().map(user -> MODEL_MAPPER.map(user, UsersWithSoldProductsDto.class));
//
//        List<UsersWithSoldProductsDto> collect = usersWithSoldProductsDtoStream.collect(Collectors.toList());


        final List<UsersWithSoldProductsDto> usersWithSoldProductsDtoList = this.userRepository
                .findAllBySellingProductsBuyerIsNotNullOrderBySellingProductsBuyerFirstName()
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(user -> MODEL_MAPPER.map(user, UsersWithSoldProductsDto.class))
                .toList();

        writeJsonIntoFile(usersWithSoldProductsDtoList, USERS_WITH_SOLD_PRODUCTS_JSON_PATH);

        return usersWithSoldProductsDtoList;
    }
}
