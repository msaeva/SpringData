package entities;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "planes")
public class Plane extends Vehicle {
    private static final String PLANE_TYPE = "BIKE";
    @Basic
    private int passengersCapacity;

    protected Plane() {
        super(PLANE_TYPE);
    }

    public Plane(String model, int passengersCapacity) {
        this();

        this.model = model;

        this.passengersCapacity = passengersCapacity;
    }

    public int getPassengersCapacity() {
        return passengersCapacity;
    }

    public void setPassengersCapacity(int passengersCapacity) {
        this.passengersCapacity = passengersCapacity;
    }
}
