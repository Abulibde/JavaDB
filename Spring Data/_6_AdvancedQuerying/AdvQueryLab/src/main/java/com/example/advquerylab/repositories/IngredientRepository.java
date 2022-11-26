package com.example.advquerylab.repositories;

import com.example.advquerylab.entities.Ingredient;
import com.example.advquerylab.entities.Shampoo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    //List<Ingredient> selectByNameIn(List<String> names);

    List<Ingredient> findByNameIn(List<String> names);
}
