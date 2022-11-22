package com.example.cardealer.domain.entities.dtos.car;

import com.example.cardealer.domain.entities.dtos.part.PartWithNameDto;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class CarWithPartsDto {

    @SerializedName("Make")
    private String make;

    @SerializedName("Model")
    private String model;

    @SerializedName("TravelledDistance")
    private String travelledDistance;

    public Set<PartWithNameDto> parts;

}
