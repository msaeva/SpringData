package com.example.cardealer.services;

import com.example.cardealer.domain.entities.Car;
import com.example.cardealer.domain.entities.dtos.car.CarToyotaDto;
import com.example.cardealer.domain.entities.dtos.car.CarToyotaWrapperDto;
import com.example.cardealer.domain.entities.dtos.car.CarWithPartsDto;
import com.example.cardealer.domain.entities.dtos.car.CarWithPartsWrapperDto;
import com.example.cardealer.repositories.CarRepository;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static com.example.cardealer.domain.entities.constants.Utils.*;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<CarToyotaDto> findByMakeOrderByModelAscTravelledDistanceDesc(String model) throws IOException, JAXBException {
        List<Car> cars = carRepository.findByMakeOrderByModelAscTravelledDistanceDesc(model)
                .orElseThrow(NoSuchElementException::new);

        List<CarToyotaDto> carsDto = cars.stream()
                .map(car -> MODEL_MAPPER.map(car, CarToyotaDto.class))
                .collect(Collectors.toList());

        writeJsonIntoFile(carsDto, Path.of("src/main/resources/output/json/toyota-cars.json"));

        CarToyotaWrapperDto carToyotaWrapperDto = new CarToyotaWrapperDto(carsDto);

        writeXmlToFile(carToyotaWrapperDto, Path.of("src/main/resources/output/xml/toyota-cars.xml"));

        return carsDto;
    }

    @Override
    public List<CarWithPartsDto> getAllCarsWithParts() throws IOException, JAXBException {
        List<Car> cars = carRepository.findAll();

        List<CarWithPartsDto> carWithoutPartsDtos = cars.stream()
                .map(car -> MODEL_MAPPER.map(car, CarWithPartsDto.class))
                .collect(Collectors.toList());

        writeJsonIntoFile(carWithoutPartsDtos, Path.of("src/main/resources/output/json/cars-and-parts.json"));

        CarWithPartsWrapperDto carWithPartsWrapperDto = new CarWithPartsWrapperDto(carWithoutPartsDtos);


        writeXmlToFile(carWithPartsWrapperDto, Path.of("src/main/resources/output/xml/cars-and-parts.xml"));

        return carWithoutPartsDtos;
    }
}
