package com.example.advquerylab.services;

import com.example.advquerylab.entities.Ingredient;
import com.example.advquerylab.repositories.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService{

    private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }


    @Override
    public List<Ingredient> selectByNames(List<String> names) {
        return this.ingredientRepository.findByNameIn(names);
    }
}
