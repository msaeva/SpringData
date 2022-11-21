package com.example.modelmapper.services;

import com.example.modelmapper.entities.Address;
import com.example.modelmapper.entities.Employee;
import com.example.modelmapper.entities.dtos.CreateEmployeeDTO;
import com.example.modelmapper.entities.dtos.EmployeeDTO;
import com.example.modelmapper.repositories.AddressRepository;
import com.example.modelmapper.repositories.EmployeeRepository;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final AddressRepository addressRepository;
    private final ModelMapper mapper;


    public EmployeeServiceImpl(EmployeeRepository employeeRepository, AddressRepository addressRepository, ModelMapper mapper) {
        this.employeeRepository = employeeRepository;
        this.addressRepository = addressRepository;
        this.mapper = mapper;

    }

    @Transactional
    @Override
    public EmployeeDTO create(CreateEmployeeDTO data) {

        Employee employee = mapper.map(data, Employee.class);

        Optional<Address> address = this.addressRepository.
                findByCountryAndCity(data.getAddress().getCountry(),
                        data.getAddress().getCity());

        address.ifPresent(employee::setAddress);

//        if (address.isPresent()){
//            employee.setAddress(address.get());
//        }
        Employee save = employeeRepository.save(employee);
        return this.mapper.map(save, EmployeeDTO.class);
    }

    @Override
    public List<EmployeeDTO> findAll() {
        return employeeRepository.findAll()
                .stream()
                .map(e -> mapper.map(e, EmployeeDTO.class))
                .collect(Collectors.toList());
    }
}
