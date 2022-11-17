package com.example.modelmapper.entities.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CreateEmployeeDTO {
    private final String firstName;
    private final String lastname;
    private final BigDecimal salary;
    private final LocalDate birthday;
    private final AddressDTO address;

    public CreateEmployeeDTO(String firstName, String lastname, BigDecimal salary, LocalDate localDate, AddressDTO address) {
        this.firstName = firstName;
        this.lastname = lastname;
        this.salary = salary;
        this.birthday = localDate;
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public AddressDTO getAddress() {
        return address;
    }
}
