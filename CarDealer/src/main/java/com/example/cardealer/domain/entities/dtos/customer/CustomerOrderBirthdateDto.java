package com.example.cardealer.domain.entities.dtos.customer;

import com.example.cardealer.domain.entities.Sale;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@AllArgsConstructor
@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerOrderBirthdateDto {

    @XmlElement
    private Long id;
    @XmlElement
    private String name;
    @XmlElement(name = "birth-date")
    private String birthDate;

    @XmlElement(name = "is-young-driver")
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
