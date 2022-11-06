package entities;

import javax.persistence.*;
import java.util.Set;

@Table
@Entity
public class Customer {

    @Column
    @Id
    private int id;

    @Column
    private String name;

    @Column
    private String email;


    @Column(name = "credit_card_number")
    private String creditCardNumber;

    @OneToMany(mappedBy = "customer")
    private Set<Sale> sales; //a customer can participate in many sales
}
