package com.example.modelmapper;

import com.example.modelmapper.entities.Address;
import com.example.modelmapper.entities.Employee;
import com.example.modelmapper.entities.dtos.EmployeeDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

// @Component
public class ModelMapperMain implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        ModelMapper mapper = new ModelMapper();
//
//        PropertyMap<Employee, EmployeeDTO> propertyMap = new PropertyMap<Employee, EmployeeDTO>() {
//            @Override
//            protected void configure() {
//                map().setCity(source.getAddress().getCity());
//            }
//        };
//
//        mapper.addMappings(propertyMap);

        // other option
//        TypeMap<Employee, EmployeeDTO> typeMap = mapper.createTypeMap(Employee.class, EmployeeDTO.class);
//
//        typeMap.addMappings(mapping -> mapping.map(
//                source -> source.getAddress().getCity(),
//                EmployeeDTO::setCity));
//
////        typeMap.validate();
//
//
//        Address address = new Address("Bulgaria", "Sofia");
//
//        Employee employee = new Employee("Ivan", BigDecimal.ONE, address);
//
//        // EmployeeDTO employeeDTO = mapper.map(employee, EmployeeDTO.class);
//
//        EmployeeDTO employeeDTO = typeMap.map(employee);
//
//        System.out.println(employeeDTO.toString());

    }
}
