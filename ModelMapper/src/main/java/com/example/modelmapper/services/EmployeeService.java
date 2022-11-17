package com.example.modelmapper.services;

import com.example.modelmapper.entities.Employee;
import com.example.modelmapper.entities.dtos.CreateEmployeeDTO;
import com.example.modelmapper.entities.dtos.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    public Employee create(CreateEmployeeDTO employeeDTO);
    List<EmployeeDTO> findAll();
}
