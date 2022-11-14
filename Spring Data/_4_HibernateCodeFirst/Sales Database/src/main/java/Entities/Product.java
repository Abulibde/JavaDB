package Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Table(name = "Products")
public class Product extends BaseEntity{

    //(id, name, quantity, price)

    @Column
    private String name;

    @Column
    private int quantity;

    @Column
    private double price;
}

