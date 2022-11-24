package com.example.cardealer.services;

import com.example.cardealer.domain.entities.*;
import com.example.cardealer.domain.entities.dtos.car.CarImportDto;
import com.example.cardealer.domain.entities.dtos.car.CarImportWrapperDto;
import com.example.cardealer.domain.entities.dtos.customer.CustomerImportDto;
import com.example.cardealer.domain.entities.dtos.customer.CustomerImportWrapperDto;
import com.example.cardealer.domain.entities.dtos.part.PartImportDto;
import com.example.cardealer.domain.entities.dtos.part.PartImportWrapperDto;
import com.example.cardealer.domain.entities.dtos.supplier.SupplierImportDto;
import com.example.cardealer.domain.entities.dtos.supplier.SupplierImportXmlWrapperDto;
import com.example.cardealer.repositories.*;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.LongStream;

import static com.example.cardealer.domain.entities.constants.Utils.GSON;
import static com.example.cardealer.domain.entities.constants.Utils.MODEL_MAPPER;

@Service
public class SeedServiceImpl implements SeedService {
    private final SupplierRepository supplierRepository;
    private final PartRepository partRepository;

    private final SaleRepository saleRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;


    public SeedServiceImpl(SupplierRepository supplierRepository, PartRepository partRepository, SaleRepository saleRepository, CarRepository carRepository, CustomerRepository customerRepository) {
        this.supplierRepository = supplierRepository;
        this.partRepository = partRepository;
        this.saleRepository = saleRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void seedSuppliers() throws IOException, JAXBException {
        if (supplierRepository.count() == 0) {
            FileReader fileReader = new FileReader("src/main/resources/dbContent/json/suppliers.json");


//            List<Supplier> suppliers = Arrays.stream(GSON.fromJson(fileReader, SupplierImportDto[].class))
//                    .map(supplierImportDto -> MODEL_MAPPER.map(supplierImportDto, Supplier.class))
//                    .toList();
//
//            fileReader.close();

            final FileReader fileReaderXml = new FileReader("src/main/resources/dbContent/xml/suppliers.xml");

            final JAXBContext context = JAXBContext.newInstance(SupplierImportXmlWrapperDto.class);

            final Unmarshaller unmarshaller = context.createUnmarshaller();

            final SupplierImportXmlWrapperDto supplierImportDto = (SupplierImportXmlWrapperDto) unmarshaller.unmarshal(fileReader);

            List<Supplier> suppliers = supplierImportDto.getSuppliers().stream()
                    .map(s -> MODEL_MAPPER.map(s, Supplier.class))
                    .toList();

            this.supplierRepository.saveAllAndFlush(suppliers);
        }

    }

    @Override
    public void seedParts() throws IOException, JAXBException {
        if (partRepository.count() == 0) {
//            final FileReader fileReader = new FileReader("src/main/resources/dbContent/parts.json");
//
//            final List<Part> parts = Arrays.stream(GSON.fromJson(fileReader, PartImportDto[].class))
//                    .map(partImportDto -> MODEL_MAPPER.map(partImportDto, Part.class))
//                    .map(this::setRandomSupplier)
//                    .toList();
//
//            fileReader.close();

            final FileReader fileReader = new FileReader("src/main/resources/dbContent/parts.xml");

            JAXBContext context = JAXBContext.newInstance(PartImportWrapperDto.class);
            final Unmarshaller unmarshaller = context.createUnmarshaller();

            PartImportWrapperDto partImportWrapperDto = (PartImportWrapperDto) unmarshaller.unmarshal(fileReader);

            List<Part> parts = partImportWrapperDto.getParts().stream()
                    .map(p -> MODEL_MAPPER.map(p, Part.class))
                    .map(this::setRandomSupplier)
                    .toList();

            this.partRepository.saveAllAndFlush(parts);
        }
    }


    @Override
    public void seedCars() throws IOException, JAXBException {

//        if (carRepository.count() == 0) {
//            final FileReader fileReader = new FileReader("src/main/resources/dbContent/cars.json");
//
//            List<Car> cars = Arrays.stream(GSON.fromJson(fileReader, CarImportDto[].class))
//                    .map(carImportDto -> MODEL_MAPPER.map(carImportDto, Car.class))
//                    .map(this::setRandomParts)
//                    .toList();
//
//            fileReader.close();

        final FileReader fileReader = new FileReader("src/main/resources/dbContent/cars.xml");

        JAXBContext context = JAXBContext.newInstance(CarImportWrapperDto.class);
        final Unmarshaller unmarshaller = context.createUnmarshaller();

        CarImportWrapperDto carImportWrapperDto = (CarImportWrapperDto) unmarshaller.unmarshal(fileReader);

        List<Car> cars = carImportWrapperDto.getCars().stream()
                .map(c -> MODEL_MAPPER.map(c, Car.class))
                .map(this::setRandomParts)
                .toList();

        this.carRepository.saveAllAndFlush(cars);
    }
    @Override
    public void seedCustomers() throws IOException, JAXBException {
        if (customerRepository.count() == 0) {
//            final FileReader fileReader = new FileReader("src/main/resources/dbContent/customers.json");
//
//            List<Customer> customers = Arrays.stream(GSON.fromJson(fileReader, CustomerImportDto[].class))
//                    .map(customerImportDto -> MODEL_MAPPER.map(customerImportDto, Customer.class))
//                    .toList();
//
//            fileReader.close();

            final FileReader fileReader = new FileReader("src/main/resources/dbContent/customers.xml");

            final FileReader fileReaderXml = new FileReader("src/main/resources/dbContent/xml/suppliers.xml");

            final JAXBContext context = JAXBContext.newInstance(CustomerImportWrapperDto.class);

            final Unmarshaller unmarshaller = context.createUnmarshaller();

            final CustomerImportWrapperDto supplierImportDto = (CustomerImportWrapperDto) unmarshaller.unmarshal(fileReader);

            List<Customer> customers = supplierImportDto.getCustomers()
                    .stream().map(s -> MODEL_MAPPER.map(s, Customer.class)).toList();

            this.customerRepository.saveAllAndFlush(customers);
        }
    }
    @Override
    public void seedSales() {
        if (saleRepository.count() == 0) {

            final Random random = new Random();

            long numberOfSales = random.nextLong(carRepository.count());

            List<Sale> sales = new ArrayList<>();

            Set<Car> saleCars = new HashSet<>();

            for (int i = 0; i < numberOfSales; i++) {
                Car car = carRepository.getRandomEntity().orElseThrow(NoSuchElementException::new);

                while (saleCars.contains(car)) {
                    car = carRepository.getRandomEntity().orElseThrow(NoSuchElementException::new);
                }

                saleCars.add(car);

                Customer customer = customerRepository.getRandomEntity()
                        .orElseThrow(NoSuchElementException::new);

                int discount = getRandomDiscount();

                Sale newSale = new Sale(discount, car, customer);
                sales.add(newSale);
            }
            saleRepository.saveAllAndFlush(sales);
        }

    }


    public Car setRandomParts(Car car) {
        final Random random = new Random();

        final long numberOfParts = random.nextLong(3, 6);

        final Set<Part> parts = new HashSet<>();

        LongStream.range(0, numberOfParts)
                .forEach(number -> {
                    Part part = this.partRepository.getRandomEntity().orElseThrow(NoSuchElementException::new);

                    while (parts.contains(part)) {
                        part = this.partRepository.getRandomEntity().orElseThrow(NoSuchElementException::new);
                    }

                    parts.add(part);
                });

        car.setParts(parts);

        return car;
    }

    private Part setRandomSupplier(Part part) {
        Supplier supplier = this.supplierRepository.getRandomEntity().orElseThrow(NoSuchElementException::new);

        part.setSupplier(supplier);

        return part;
    }



    public int getRandomDiscount() {
        ArrayList<Integer> percentages = new ArrayList<>(List.of(0, 5, 10, 15, 20, 30, 40, 50));
        Random random = new Random();

        Integer percent = percentages.get(random.nextInt(percentages.size()));

        return percent;

    }

}
