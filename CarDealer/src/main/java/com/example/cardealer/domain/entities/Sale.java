package com.example.cardealer.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "sales")
public class Sale extends BaseEntity {
    @OneToOne
    private Car car;

    @ManyToOne(optional = false)
    private Customer customer;
    @Column
    private Integer discount;


    public Sale(Integer discount, Car car, Customer customer) {
        this.car = car;
        this.customer = customer;
        setDiscount(discount);
    }

    public void setDiscount(Integer discount) {
        if (customer.isYoungDriver()) {
            discount += 5;
        }
        this.discount = discount;
    }
}
