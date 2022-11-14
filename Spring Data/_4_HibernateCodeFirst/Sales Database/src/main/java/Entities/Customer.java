package Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table
public class Customer extends BaseEntity{

    //id, name, email, credit_card_number)

    @Column
    private String name;

    @Column
    private String email;

    @Column(name = "credit_card_number")
    private String creditCardNumber;
}

