package com.example.cardealer.domain.entities.dtos.sale;

import com.example.cardealer.domain.entities.dtos.car.CarWithoutPartsDto;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class SaleWithDiscountDto {

    public CarWithoutPartsDto car;

    public String customerName;

    @SerializedName("Discount")
    public double discount;

    public BigDecimal price;

    public BigDecimal priceWithDiscount;

}
