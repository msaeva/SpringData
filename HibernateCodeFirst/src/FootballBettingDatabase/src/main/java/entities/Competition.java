package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table
public class Competition  extends BaseEntity{
    @Column
    private String name;

    @ManyToOne
    @JoinColumn
    private CompetitionType type;
}
