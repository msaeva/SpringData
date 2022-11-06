package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@Table
@Entity
public class Bet extends BaseEntity{
    @Column(name = "bet_money")
    private BigInteger betMoney;

    @Column(name = "time_of_bet")
    private Date timeOfBet;

    @JoinColumn
    @ManyToOne
    private User user;

}
