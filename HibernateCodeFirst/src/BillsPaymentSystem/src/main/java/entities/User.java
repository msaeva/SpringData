package entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
public class User {
    @Id
    private int id;

    @Column(name = "first_name",length = 30, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 30, nullable = false)
    private String lastName;

    @Column(length = 30, nullable = false, unique = true)
    private String email;

    @Column(nullable = false, length = 30)
    private String password;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private Set<BillingDetail> billingDetails;
}
