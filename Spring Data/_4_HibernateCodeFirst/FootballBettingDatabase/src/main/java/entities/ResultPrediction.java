package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class ResultPrediction extends BaseEntity{

    //Id, Prediction (possible values - Home Team Win, Draw Game, Away Team Win)

    @Enumerated(EnumType.STRING)
    private ResultPredictionValues resultPrediction;
}
