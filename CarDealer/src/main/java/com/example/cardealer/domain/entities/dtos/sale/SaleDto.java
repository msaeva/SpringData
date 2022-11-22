package com.example.cardealer.domain.entities.dtos.sale;

import com.example.cardealer.domain.entities.dtos.car.CarDto;
import com.example.cardealer.domain.entities.dtos.car.CarWithoutPartsDto;
import com.example.cardealer.domain.entities.dtos.customer.CustomerDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class SaleDto {
    private Integer discount;
    private CarDto car;
    private CustomerDto customer;

    public SaleWithDiscountDto saleWithDiscountDto() {
        CarWithoutPartsDto car = new CarWithoutPartsDto(getCar().getMake(), getCar().getModel(), getCar().getTravelledDistance());

        BigDecimal price = this.car.getCarPrice();

        BigDecimal priceAfterDiscount = price.subtract(price.multiply(BigDecimal.valueOf(discount / 100.0)));

        return new SaleWithDiscountDto(car, customer.getName(), discount / 100.0, price, priceAfterDiscount);
    }
}