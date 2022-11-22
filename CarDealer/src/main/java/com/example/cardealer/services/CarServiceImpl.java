package com.example.cardealer.services;

import com.example.cardealer.domain.entities.Car;
import com.example.cardealer.domain.entities.dtos.car.CarToyotaDto;
import com.example.cardealer.domain.entities.dtos.car.CarWithPartsDto;
import com.example.cardealer.domain.entities.dtos.car.CarWithoutPartsDto;
import com.example.cardealer.repositories.CarRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static com.example.cardealer.domain.entities.constants.Utils.MODEL_MAPPER;
import static com.example.cardealer.domain.entities.constants.Utils.writeJsonIntoFile;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> findByMakeOrderByModelAscTravelledDistanceDesc(String model) throws IOException {
        List<Car> cars = carRepository.findByMakeOrderByModelAscTravelledDistanceDesc(model)
                .orElseThrow(NoSuchElementException::new);

        List<CarToyotaDto> carsDto = cars.stream()
                .map(car -> MODEL_MAPPER.map(car, CarToyotaDto.class))
                .collect(Collectors.toList());

        writeJsonIntoFile(carsDto, Path.of("src/main/resources/output/toyota-cars.json"));
        return cars;
    }

    @Override
    public List<Car> getAllCarsWithParts() throws IOException {
        List<Car> cars = carRepository.findAll();

        List<CarWithPartsDto> carWithoutPartsDtos = cars.stream()
                .map(car -> MODEL_MAPPER.map(car, CarWithPartsDto.class))
                .collect(Collectors.toList());

        writeJsonIntoFile(carWithoutPartsDtos, Path.of("src/main/resources/output/cars-and-parts.json"));

        return cars;
    }
}
