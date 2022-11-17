package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class User extends BaseEntity{
    //Id, Username, Password, Email, Full Name, Balance

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private String fullName;

    @Column
    private BigDecimal balance;

}
