package entities;

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
public class PlayersStatistic  implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @Id
    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    //Game, Player, Scored Goals, Player Assists, Played Minutes During Game, (PK = Game + Player)

    @Column(name = "scored_goals")
    private short scoredGoals;

    @Column(name = "player_assists")
    private short playerAssists;

    @Column(name = "minutes_played")
    private short minutesPlayed;



}
