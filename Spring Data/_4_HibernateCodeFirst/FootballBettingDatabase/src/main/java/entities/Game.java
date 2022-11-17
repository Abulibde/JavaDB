package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class Game extends BaseEntity implements Serializable {

    /*
    Id, Home Team, Away Team, Home Goals, Away Goals, Date and Time of Game,
    Home team Win bet rate, Away Team Win Bet Rate, Draw Game Bet Rate, Round, Competition)
     */
    @OneToOne
    @JoinColumn
    private Team homeTeam;

    @OneToOne
    @JoinColumn
    private Team awayTeam;

    @Column
    private short homeGoals;

    @Column
    private short awayGoals;

    @Column
    private Date dateAndTime;

    @Column
    private double homeTeamWinBetRate;

    @Column
    private double awayTeamWinBetRate;

    @Column
    private double drawTeamWinBetRate;

    @ManyToOne
    private Round round;

    @ManyToOne
    private Competition competition;




}
