package entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter @Getter @NoArgsConstructor
@Table
@Entity
public class BetGame implements Serializable {

    @Id
    @OneToOne
    private Game game;

    @Id
    @OneToOne
    private Bet net;

    @OneToOne
    @JoinColumn
    private ResultPrediction resultPrediction;
}
