package com.example.cardealer.services;

import com.example.cardealer.domain.entities.dtos.customer.CustomerOrderBirthdateDto;
import com.example.cardealer.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.NoSuchElementException;

import static com.example.cardealer.domain.entities.constants.Utils.MODEL_MAPPER;
import static com.example.cardealer.domain.entities.constants.Utils.writeJsonIntoFile;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerOrderBirthdateDto> getAllCustomersOrderByBirthday() throws IOException {
        List<CustomerOrderBirthdateDto> customers = this.customerRepository.findAllByOrderByBirthDateAscYoungDriverDesc()
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(customer -> MODEL_MAPPER.map(customer, CustomerOrderBirthdateDto.class))
                .toList();

        writeJsonIntoFile(customers, Path.of("src/main/resources/output/ordered-customers.json"));
        return customers;
    }
}
