package com.example.cardealer.services;

import com.example.cardealer.domain.entities.dtos.customer.CustomerOrderBirthdateDto;

import java.io.IOException;
import java.util.List;

public interface CustomerService {
    List<CustomerOrderBirthdateDto> getAllCustomersOrderByBirthday() throws IOException;
}
