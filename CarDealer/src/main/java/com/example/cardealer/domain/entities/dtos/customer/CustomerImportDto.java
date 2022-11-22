package com.example.cardealer.domain.entities.dtos.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class CustomerImportDto {
    private String name;
    private LocalDateTime birthDate;
    private boolean isYoungDriver;
}