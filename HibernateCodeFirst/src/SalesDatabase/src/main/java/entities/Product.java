package entities;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table
public class Product {
    @Id
    @Column
    private int id;

    @Column
    private String name;

    @Column
    private int quantity;

    @Column
    private double price;

    @OneToMany(mappedBy = "product")
    private Set<Sale> saleSet; // one product is in many sales
}
