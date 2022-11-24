package com.example.cardealer.domain.entities.dtos.part;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@XmlRootElement(name = "parts")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartWithNameAndPriceWrapperDto {
    @XmlElement(name = "part")
    private List<PartWithNameAndPriceDto> parts;
}
