package com.example.cardealer.services;

import com.example.cardealer.domain.entities.dtos.car.CarToyotaDto;
import com.example.cardealer.domain.entities.dtos.car.CarWithPartsDto;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public interface CarService {
    List<CarToyotaDto> findByMakeOrderByModelAscTravelledDistanceDesc(String make) throws IOException, JAXBException;

    List<CarWithPartsDto> getAllCarsWithParts() throws IOException, JAXBException;
}
