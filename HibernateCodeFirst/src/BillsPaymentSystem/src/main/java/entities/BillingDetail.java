package entities;

import javax.persistence.*;

@Entity
@Table
public class BillingDetail {

    @Id
    @Column
    private String number;

    @ManyToOne
    private User owner;
}
