package com.example.modelmapper;

import com.example.modelmapper.entities.Employee;
import com.example.modelmapper.entities.dtos.AddressDTO;
import com.example.modelmapper.entities.dtos.CreateAddressDTO;
import com.example.modelmapper.entities.dtos.CreateEmployeeDTO;
import com.example.modelmapper.entities.dtos.EmployeeDTO;
import com.example.modelmapper.services.AddressService;
import com.example.modelmapper.services.EmployeeService;
import com.google.gson.Gson;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

@Component
public class AppMain implements CommandLineRunner {
    private final AddressService addressService;
    private final EmployeeService employeeService;

    private final Scanner scanner;
    private final Gson gson;

    public AppMain(AddressService addressService, EmployeeService employeeService, Scanner scanner, Gson gson) {
        this.addressService = addressService;
        this.employeeService = employeeService;
        this.scanner = scanner;
        this.gson = gson;
    }

    @Override
    public void run(String... args) throws Exception {
       // createAddress();
        createEmployee();
        // printAllEmployees();
    }

    private void createEmployee() {

        String input = scanner.nextLine();

        CreateEmployeeDTO employeeDTO = this.gson.fromJson(input, CreateEmployeeDTO.class);

        EmployeeDTO employee = employeeService.create(employeeDTO);

        this.gson.toJson(employee);
    }

    private void createAddress() {

        String input = scanner.nextLine();

        CreateAddressDTO data = this.gson.fromJson(input, CreateAddressDTO.class);

        AddressDTO created = addressService.create(data);
        System.out.println(this.gson.toJson(created));
    }

    private void printAllEmployees() {
        this.employeeService.findAll().forEach(System.out::println);
    }
}
