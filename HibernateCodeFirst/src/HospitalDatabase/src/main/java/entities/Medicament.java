package entities;

import javax.persistence.*;
import java.util.Set;

@Table
@Entity
public class Medicament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    private String name;

    @ManyToMany(mappedBy = "medicaments")
    private Set<Patient> patients;

}
