package com.example.cardealer.domain.entities.dtos.part;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@XmlRootElement(name = "part")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartWithNameAndPriceDto {
    @SerializedName("Name")
    @XmlAttribute
    private String name;

    @SerializedName("Price")
    @XmlAttribute
    private BigDecimal price;
}
