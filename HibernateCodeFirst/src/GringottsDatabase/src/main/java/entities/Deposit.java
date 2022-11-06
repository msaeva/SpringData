package entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class Deposit {

    @Id
    @Column
    private Long id;

    @Column(length = 20)
    private long group;

    @Column
    private LocalDate startDate;

    @Column
    private Double amount;

    @Column
    private Double interest;

    @Column
    private Double charge;

    @Column
    private LocalDate expirationDate;

    @Column
    private boolean isExpired;


}
