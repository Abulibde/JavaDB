package com.example.advquerylab.services;


import com.example.advquerylab.entities.Ingredient;

import java.util.List;

public interface IngredientService {
    List<Ingredient> selectByNames(List<String> names);
}
