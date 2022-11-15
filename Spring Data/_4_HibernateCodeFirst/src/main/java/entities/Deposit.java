package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table
public class Deposit {
    @Id
    @Column
    private long group;

    @Column(length = 20)
    private LocalDate startDate;

    @Column
    private double amount;

    @Column
    private double interest;

    @Column
    private double charge;

    @Column
    private LocalDate expirationDate;

    @Column
    private boolean isDepositExpired;

}
