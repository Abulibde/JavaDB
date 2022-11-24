package University.System.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table
public class Course {
//(id, name, description, start date, end date, credits)

    @Id
    @Column
    private long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Date startDate;

    @Column
    private Date endDate;

    @Column
    private int credits;

    @ManyToMany
    @JoinTable(name = "courses_students",
            joinColumns = @JoinColumn(name = "course_id",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "student_id",referencedColumnName = "id")
    )
    private Set<Student> students;

    @ManyToOne
    private Teacher teacher;

}
