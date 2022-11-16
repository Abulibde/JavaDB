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
public class Town extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @ManyToOne()
    private Country country;
}
