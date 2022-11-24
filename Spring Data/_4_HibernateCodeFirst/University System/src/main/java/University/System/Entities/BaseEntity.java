package University.System.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table

public abstract class BaseEntity {

    @Id
    @Column
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column
    private int phoneNumber;
}
