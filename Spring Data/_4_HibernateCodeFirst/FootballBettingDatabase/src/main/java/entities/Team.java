package entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Getter
@Setter
@NoArgsConstructor

@Table
public class Team extends BaseEntity{

    @Column(nullable = false)
    private String name;

    @Column
    private String logo;

    @Column(length = 4, nullable = false)
    private String initials;

    @ManyToOne
    @JoinColumn
    private Colour primaryColour;

    @ManyToOne
    @JoinColumn
    private Colour secondaryColour;

    @ManyToOne
    private Town town;

    @Column
    private BigInteger budget;
}
