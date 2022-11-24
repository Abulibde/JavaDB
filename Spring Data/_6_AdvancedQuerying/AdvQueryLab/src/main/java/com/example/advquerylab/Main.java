package com.example.advquerylab;

import com.example.advquerylab.entities.Ingredient;
import com.example.advquerylab.entities.Shampoo;
import com.example.advquerylab.entities.Size;
import com.example.advquerylab.services.ShampooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class Main implements CommandLineRunner {

    private final ShampooService shampooService;

    @Autowired
    public Main(ShampooService shampooService) {
        this.shampooService = shampooService;
    }

    @Override
    public void run(String... args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        String price = scanner.nextLine();

       /* List<String> ingredients = new ArrayList<>();

        while (!nextLine.isBlank()) {
            ingredients.add(nextLine);
            nextLine = scanner.nextLine();
        }*/

        for (Shampoo shampoo : shampooService.findWithPriceGreaterThen(price)) {
            System.out.println(shampoo);
        }
    }
}
