package productsShop.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column
    private BigDecimal price;

    @ManyToOne
    private User buyer;

    @ManyToOne
    private User seller;

    @ManyToMany
    private Set<Category> categories;

    public Product() {
        this.categories = new HashSet<>();
    }

    public Product(String name, BigDecimal price) {
        this();
        this.name = name;
        this.price = price;
    }
}
