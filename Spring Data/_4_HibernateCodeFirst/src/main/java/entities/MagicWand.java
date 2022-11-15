package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table
public class MagicWand {
    @Id
    @Column
    private Long id;

    @Column(length = 100)
    private String creator;

    @Column

    private Long size;
}
