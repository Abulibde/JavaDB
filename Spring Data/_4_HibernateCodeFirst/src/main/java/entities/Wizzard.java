package entities;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table
public class Wizzard {
    @Id
    @Column
    private long id;

    @Column(length = 50)
    private String firstName;

    @Column(length = 60, nullable = true)
    private String lastName;

    @Column(length = 1000)
    private String note;

    @Column(nullable = true)
    private String age;

    @OneToOne
    @Column
    @JoinTable
    private MagicWand magicWand;

    @OneToMany
    @Column
    private List<Deposit> deposits;
}
