package entities;

import javax.persistence.*;
import java.util.Date;

@Table
@Entity
public class Sale {
    @Column
    @Id
    private int id;

    @ManyToOne
    @JoinColumn
    private Product product; // sale has one product

    @ManyToOne
    @JoinColumn
    private Customer customer;

    @ManyToOne
    @JoinColumn
    private StoreLocation storeLocation;

    @Column
    private Date date;
}
