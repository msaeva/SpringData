package com.example.cardealer.domain.entities.dtos.car;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CarToyotaDto {
    private Long id;

    private String make;

    private String model;

    private Long travelledDistance;
}
