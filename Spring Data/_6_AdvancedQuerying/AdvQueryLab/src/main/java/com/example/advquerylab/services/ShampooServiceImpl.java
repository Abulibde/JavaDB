package com.example.advquerylab.services;

import com.example.advquerylab.entities.Shampoo;
import com.example.advquerylab.entities.Size;
import com.example.advquerylab.repositories.ShampooRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ShampooServiceImpl implements ShampooService {

    private final ShampooRepository shampooRepository;

    public ShampooServiceImpl(ShampooRepository shampooRepository) {
        this.shampooRepository = shampooRepository;
    }

    @Override
    public List<Shampoo> findByBrand(String brand) {
        return this.shampooRepository.findByBrand(brand);
    }

    public List<Shampoo> findByBrandAndSize(String brand, String size) {
        Size parsed = Size.valueOf(size.toUpperCase());
        return shampooRepository.findByBrandAndSize(brand, parsed);
    }

    public List<Shampoo> findBySizeOrderByIdDesc(Size size) {
        return shampooRepository.findBySizeOrderByIdDesc(size);
    }

    @Override
    public List<Shampoo> findByIngredient(String ingredient) {
        return shampooRepository.findByIngredient(ingredient);
    }

    @Override
    public List<Shampoo> findByIngredients(List<String> ingredients) {
        return shampooRepository.findByIngredients(ingredients);
    }

    @Override
    public List<Shampoo> findWithPriceGreaterThen(String price){
        BigDecimal parsed = new BigDecimal(price);
        return this.shampooRepository.findByPriceGreaterThanOrderByPriceDesc(parsed);
    }
}
