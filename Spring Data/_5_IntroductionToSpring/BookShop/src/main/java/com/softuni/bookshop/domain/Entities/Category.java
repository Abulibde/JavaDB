package com.softuni.bookshop.domain.Entities;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Category extends BaseEntity {

    @Column
    private String name;

    //@ManyToMany(targetEntity = Book.class, mappedBy = "categories")
    //private Set<Book> books;
}
