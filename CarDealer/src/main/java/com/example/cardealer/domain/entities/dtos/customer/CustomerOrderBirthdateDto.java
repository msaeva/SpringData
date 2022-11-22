package com.example.cardealer.domain.entities.dtos.customer;

import com.example.cardealer.domain.entities.Sale;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerOrderBirthdateDto {
    private Long id;

    private String name;

    private String birthDate;

    private boolean youngDriver;

    private Sale[] sales;

    public CustomerOrderBirthdateDto() {
        this.sales = new Sale[0];
    }

    public CustomerOrderBirthdateDto(Long id, String name, String birthDate, boolean youngDriver) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.youngDriver = youngDriver;
        this.sales = new Sale[0];
    }

    public void setSales(Sale[] sales) {
        this.sales = new Sale[0];
    }
}
