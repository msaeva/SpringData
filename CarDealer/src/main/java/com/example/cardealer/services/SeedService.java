package com.example.cardealer.services;

import com.example.cardealer.domain.entities.dtos.customer.CustomerOrderBirthdateDto;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public interface SeedService {
    void seedSuppliers() throws IOException, JAXBException;

    void seedParts() throws IOException, JAXBException;

    void seedCars() throws IOException, JAXBException;

    void seedCustomers() throws IOException, JAXBException;

    void seedSales();

}
