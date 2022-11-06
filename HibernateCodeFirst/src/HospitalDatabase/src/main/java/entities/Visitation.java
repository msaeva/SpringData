package entities;

import javax.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table
public class Visitation {

    @Id
    private int id;

    @Basic
    private Date date;

    @Basic
    private String comments;

    @ManyToMany(mappedBy = "visitations")
    private Set<Patient> patients;
}
