package com.example.cardealer.services;

import com.example.cardealer.domain.entities.Car;

import java.io.IOException;
import java.util.List;

public interface CarService {
    List<Car> findByMakeOrderByModelAscTravelledDistanceDesc(String make) throws IOException;

    List<Car> getAllCarsWithParts() throws IOException;
}
