package hasEntities;

import javax.persistence.*;

@Entity
@Table(name = "has_plate_numbers")
public class PlateNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String number;

    @OneToOne(targetEntity = Truck.class, mappedBy = "plateNumber") // двупосочна връзка; "plateNumber" -> името на полето
    private Truck truck;

    public PlateNumber(){

    }

    public PlateNumber(String number) {
        this.number = number;
    }
}
