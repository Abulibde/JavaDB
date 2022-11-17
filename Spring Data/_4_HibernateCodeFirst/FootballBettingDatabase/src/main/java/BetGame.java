import entities.Bet;
import entities.Game;
import entities.ResultPrediction;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class BetGame implements Serializable {

    //Game, Bet, Result Prediction (PK = Game + Bet)

    @Id
    @OneToOne
    private Game game;

    @Id
    @OneToOne
    private Bet bet;

    @OneToOne
    @JoinColumn
    private ResultPrediction resultPrediction;

}
