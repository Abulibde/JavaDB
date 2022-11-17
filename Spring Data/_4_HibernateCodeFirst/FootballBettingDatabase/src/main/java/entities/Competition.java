package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class Competition extends BaseEntity{

    //d, Name, Type (local, national, international)

    @Column
    private String name;

    @ManyToOne
    private CompetitionType type;
}
