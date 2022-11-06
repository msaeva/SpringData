package org.example;

import javax.persistence.Entity;

@Entity
public class Truck extends TransportationVehicle {
    private final static String type = "TRUCK";
    private int numOfContainers;

    public Truck() {
    }

    public Truck(String type, int numOfContainers, int loadCapacity) {
        super(type, loadCapacity);
        this.numOfContainers = numOfContainers;
    }
}
