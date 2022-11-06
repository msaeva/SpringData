package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Table
@Entity
@Getter @Setter
@NoArgsConstructor
public class Wizard {
    @Id
    @Column
    private long id;

    @Column(name = "first_name",length = 50)
    private String firstName;

    @Column(name = "last_name", length = 60, nullable = true)
    private String lastName;

    @Column(length = 1000)
    private String note;

    @Column
    private Long age;

    @JoinTable(name = "wizzard_deposits", joinColumns = @JoinColumn(name = "id"),
    inverseJoinColumns = @JoinColumn(name = "deposit_id"))
    @OneToOne
    private MagicLand magicLand;

    @OneToMany
    private List<Deposit> deposits;
}
