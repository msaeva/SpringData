package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;


@Setter @Getter @NoArgsConstructor
@Entity
@Table(name = "teams")
public class Team  extends BaseEntity{

    @Column(nullable = false)
    private String name;

    @Column
    private String logo;

    @Column(length = 4)
    private String initials;

    @JoinColumn
    @ManyToOne
    private Color primaryColor;

    @JoinColumn
    @ManyToOne
    private Color secondaryColor;

    @ManyToOne // has many teams in one town but one team is in only one town
    private Town town;

    @Column
    private BigInteger budget;

}
