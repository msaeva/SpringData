package com.example.cardealer.domain.entities.dtos.car;

import com.example.cardealer.domain.entities.dtos.part.PartWithNameAndPriceDto;
import com.example.cardealer.domain.entities.dtos.part.PartWithNameAndPriceWrapperDto;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.Set;
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarWithPartsDto {

    @SerializedName("Make")
    @XmlAttribute
    private String make;

    @SerializedName("Model")
    @XmlAttribute
    private String model;

    @SerializedName("TravelledDistance")
    @XmlAttribute(name = "travelled-distance")
    private String travelledDistance;

//    @XmlElement(name = "part")
//    public Set<PartWithNameAndPriceDto> parts;

    public PartWithNameAndPriceWrapperDto parts;

}
