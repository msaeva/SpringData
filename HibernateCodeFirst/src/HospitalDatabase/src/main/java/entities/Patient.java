package entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name", nullable = false, length = 30)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 30)
    private String lastName;

    @Column(nullable = false, length = 100)
    private String address;

    @Column
    private Date birthDate;

    @Column
    private String picture;

    @Column(name = "medical_insurance")
    private boolean medicalInsurance;

    @ManyToMany
    @JoinTable(name = "patients_visitations", joinColumns = @JoinColumn(name = "patient_id"
            , referencedColumnName = "id")
            , inverseJoinColumns = @JoinColumn(name = "visitation_id", referencedColumnName = "id"))
    private Set<Visitation> visitations;

    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "diagnose_id", referencedColumnName = "id")
    )
    private Set<Diagnose> diagnose;

    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "medicament_id", referencedColumnName = "id"))
    private Set<Medicament> medicaments;

}
