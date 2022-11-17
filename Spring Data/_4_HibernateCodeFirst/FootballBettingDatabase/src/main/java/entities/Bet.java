package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Bets")
public class Bet extends BaseEntity{

    //Id, Bet Money, Date and Time of Bet, User

    @Column(name = "bet_money")
    private BigDecimal betMoney;

    @Column(name = "time_of_bet")
    private Date timeOfBet;

@ManyToOne
    private User user;
}
