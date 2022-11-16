package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.implementation.bind.annotation.DefaultCall;

import javax.persistence.*;


public class Player extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(name = "squad_number", nullable = false)
    private short squadNumber;

    @ManyToOne
    @JoinColumn
    private Team team;

    @ManyToOne
    private Position position;

    @Column
    private boolean isCurrentlyInjured;

}
