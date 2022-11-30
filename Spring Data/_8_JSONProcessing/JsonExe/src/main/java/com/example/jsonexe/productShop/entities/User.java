package com.example.jsonexe.productShop.entities;

import jakarta.persistence.*;

import lombok.Getter;

import lombok.Setter;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column
    private Integer age;

    @OneToMany(targetEntity = Product.class, mappedBy = "buyer")
    private List<Product> boughtProducts;

    @OneToMany(targetEntity = Product.class, mappedBy = "seller")
    private List<Product> sellingProducts;

    @ManyToMany
    private Set<User> friends;

    public User() {
        this.sellingProducts = new ArrayList<>();
        this.boughtProducts = new ArrayList<>();
        this.friends = new HashSet<>();
    }

    public User(String firstName, String lastName, Integer age) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
}
