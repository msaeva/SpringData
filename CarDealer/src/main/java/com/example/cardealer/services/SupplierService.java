package com.example.cardealer.services;

import com.example.cardealer.domain.entities.dtos.supplier.LocalSupplierDto;

import java.io.IOException;
import java.util.List;

public interface SupplierService {
    List<LocalSupplierDto> getAllLocalSuppliers() throws IOException;
}
