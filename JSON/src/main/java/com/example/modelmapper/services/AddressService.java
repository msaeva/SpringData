package com.example.modelmapper.services;

import com.example.modelmapper.entities.dtos.AddressDTO;
import com.example.modelmapper.entities.dtos.CreateAddressDTO;

public interface AddressService {
    AddressDTO create(CreateAddressDTO data);
}
