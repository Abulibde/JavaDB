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
public class Teacher {
    //(id, first name, last name, phone number, email, salary per hour)
    @Id
    @Column
    private long id;

    @Column
    private String firstName;

    @Column
    private String lastName;


    @Column
    private int phoneNumber;


    @Column
    private String email;

    @Column
    private double salaryPerHour;
}
