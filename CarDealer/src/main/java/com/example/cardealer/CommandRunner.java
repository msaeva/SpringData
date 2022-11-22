package com.example.cardealer;

import com.example.cardealer.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandRunner implements CommandLineRunner {
    private final SeedService seedService;
    private final CustomerService customerService;
    private final CarService carService;

    private final SupplierService supplierService;

    private final SaleService saleService;

    public CommandRunner(SeedService seedService, CustomerService customerService, CarService carService, SupplierService supplierService, SaleService saleService) {
        this.seedService = seedService;
        this.customerService = customerService;
        this.carService = carService;
        this.supplierService = supplierService;
        this.saleService = saleService;
    }


    @Override
    public void run(String... args) throws Exception {
     //  seedService.seedCars();
      // seedService.seedCustomers();
       // seedService.seedSales();

      // customerService.getAllCustomersOrderByBirthday();
      //  carService.findByMakeOrderByModelAscTravelledDistanceDesc("Toyota");

       // supplierService.getAllLocalSuppliers();

       // carService.getAllCarsWithParts();

        saleService.getAllSales();

    }
}
