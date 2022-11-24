package University.System.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table
public class Student {

    //(id, first name, last name, phone number, average grade, attendance)

    @Id
    @Column
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column
    private int phoneNumber;

    @Column
    private double averageGrade;

    @ManyToMany(mappedBy = "students")
    private Set<Course> attendance;



}
