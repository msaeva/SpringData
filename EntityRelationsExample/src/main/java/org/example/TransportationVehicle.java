package org.example;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class TransportationVehicle extends Vehicle {
    private int loadCapacity;

    public TransportationVehicle() {
        super();
    }

    public TransportationVehicle(String type, int loadCapacity) {
        super(type);
        this.loadCapacity = loadCapacity;

    }

}
