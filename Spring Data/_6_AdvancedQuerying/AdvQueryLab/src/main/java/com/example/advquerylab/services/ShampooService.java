package com.example.advquerylab.services;

import com.example.advquerylab.entities.Ingredient;
import com.example.advquerylab.entities.Shampoo;
import com.example.advquerylab.entities.Size;

import java.util.List;

public interface ShampooService {

    List<Shampoo> findByBrand(String brand);

    List<Shampoo> findByBrandAndSize(String brand, String size);

    List<Shampoo> findBySizeOrderByIdDesc(Size size);

    List<Shampoo> findByIngredient(String ingredient);

    List<Shampoo> findByIngredients(List<String> ingredients);

    List<Shampoo> findWithPriceGreaterThen(String price);

    List<Ingredient> selectByName(String name);
}
