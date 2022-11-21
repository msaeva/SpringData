package com.example.modelmapper.services;

import com.example.modelmapper.entities.Address;
import com.example.modelmapper.entities.dtos.AddressDTO;
import com.example.modelmapper.entities.dtos.CreateAddressDTO;
import com.example.modelmapper.repositories.AddressRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final ModelMapper mapper;

    public AddressServiceImpl(AddressRepository addressRepository, ModelMapper mapper) {
        this.addressRepository = addressRepository;
        this.mapper = mapper;
    }

    @Override
    public AddressDTO create(CreateAddressDTO data) {

        Address address = mapper.map(data, Address.class);

        Address save = addressRepository.save(address);

        return this.mapper.map(save, AddressDTO.class);
    }
}
