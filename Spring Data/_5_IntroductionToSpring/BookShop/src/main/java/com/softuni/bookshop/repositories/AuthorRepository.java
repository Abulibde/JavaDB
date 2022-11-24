package com.softuni.bookshop.repositories;

import com.softuni.bookshop.domain.Entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
