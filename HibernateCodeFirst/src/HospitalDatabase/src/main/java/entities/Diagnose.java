package entities;

import javax.persistence.*;
import java.util.Set;

@Table
@Entity
public class Diagnose {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    private String name;

    @ManyToMany(mappedBy = "diagnose")
    private Set<Patient> patient;
}
