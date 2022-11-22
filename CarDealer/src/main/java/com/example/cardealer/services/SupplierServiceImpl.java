package com.example.cardealer.services;

import com.example.cardealer.domain.entities.dtos.supplier.LocalSupplierDto;
import com.example.cardealer.repositories.SupplierRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.NoSuchElementException;

import static com.example.cardealer.domain.entities.constants.Utils.writeJsonIntoFile;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;

    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public List<LocalSupplierDto> getAllLocalSuppliers() throws IOException {
        List<LocalSupplierDto> localSupplierDtos = supplierRepository.getLocalSupplierPartCount()
                .orElseThrow(NoSuchElementException::new);

        writeJsonIntoFile(localSupplierDtos, Path.of("src/main/resources/output/local-suppliers.json"));
        return localSupplierDtos;
    }
}
