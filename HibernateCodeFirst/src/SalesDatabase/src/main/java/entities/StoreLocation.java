package entities;

import javax.persistence.*;
import java.util.Set;

@Table
@Entity
public class StoreLocation {
    @Column
    @Id
    private int id;

    @Column
    private String name;

    @OneToMany(mappedBy = "storeLocation")
    private Set<Sale> saleSet;

}
