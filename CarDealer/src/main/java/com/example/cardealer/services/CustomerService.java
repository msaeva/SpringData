package com.example.cardealer.services;

import com.example.cardealer.domain.entities.dtos.customer.CustomerOrderBirthdateDto;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public interface CustomerService {
    List<CustomerOrderBirthdateDto> getAllCustomersOrderByBirthday() throws IOException, JAXBException;
}
