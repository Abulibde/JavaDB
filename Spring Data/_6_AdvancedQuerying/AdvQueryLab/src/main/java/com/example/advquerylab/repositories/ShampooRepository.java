package com.example.advquerylab.repositories;

import com.example.advquerylab.entities.Shampoo;
import com.example.advquerylab.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ShampooRepository extends JpaRepository<Shampoo, Long> {


    List<Shampoo> findByBrand(String brand);

    List<Shampoo> findByBrandAndSize(String brand, Size size);

    List<Shampoo> findBySizeOrderByIdDesc(Size size);

    @Query("SELECT s FROM Shampoo AS s " +
            "JOIN s.ingredients AS i " +
            "WHERE i.name = :name")
    List<Shampoo> findByIngredient(@Param("name") String ingredient);


    @Query("SELECT s FROM Shampoo AS s " +
            "JOIN s.ingredients AS i " +
            "WHERE  i.name IN :ingredients")
    List<Shampoo> findByIngredients(List<String> ingredients);


    List<Shampoo> findByPriceGreaterThanOrderByPriceDesc(BigDecimal price);
}
