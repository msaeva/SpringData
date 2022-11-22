package com.example.cardealer.services;

import com.example.cardealer.domain.entities.dtos.customer.CustomerOrderBirthdateDto;

import java.io.IOException;
import java.util.List;

public interface SeedService {
    void seedSuppliers() throws IOException;

    void seedParts() throws IOException;

    void seedCars() throws IOException;

    void seedCustomers() throws IOException;

    void seedSales();

}
