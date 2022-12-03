package com.example.football.service.impl;

import com.example.football.models.dto.ImportTownDTO;
import com.example.football.models.entity.Town;
import com.example.football.repository.TownRepository;
import com.example.football.service.TownService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


//ToDo - Implement all methods
@Service
public class TownServiceImpl implements TownService {


    private static final String INVALID_TOWN = "Invalid Town";
    private static final String SUCCESSFULLY_ADDED_TOWN = "Successfully imported Town %s - %d";
    private final TownRepository townRepository;
    private final Gson gson;
    private final Validator validator;
    private final ModelMapper modelmapper;

    @Autowired
    public TownServiceImpl(TownRepository townRepository) {
        this.townRepository = townRepository;

        this.gson = new GsonBuilder().create();

        this.validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();

        this.modelmapper = new ModelMapper();
    }


    @Override
    public boolean areImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {

        Path path = Path.of("src", "main", "resources", "files", "json", "towns.json");

        //  return String.join("\n", Files.readAllLines(path));

        return Files.readString(path);
    }

    @Override
    public String importTowns() throws IOException {

        String json = this.readTownsFileContent();

        ImportTownDTO[] importTownDTOs = this.gson.fromJson(json, ImportTownDTO[].class);

        List<String> result = new ArrayList<>();

        for (ImportTownDTO importTownDTO : importTownDTOs) {
            Set<ConstraintViolation<ImportTownDTO>> validationErrors =
                    this.validator.validate(importTownDTO);

            if (validationErrors.isEmpty()) {
                //valid DTO

                Optional<Town> optTown = this.townRepository.findByName(importTownDTO.getName());

                if (optTown.isEmpty()) {
                    //valid DTO

                    Town town = this.modelmapper.map(importTownDTO, Town.class);

                    this.townRepository.save(town);

                    String message = String.format
                            (SUCCESSFULLY_ADDED_TOWN, town.getName(), town.getPopulation());

                    result.add(message);

                } else {
                    //invalid DTO
                    result.add(INVALID_TOWN);
                }
            } else {
                //invalid DTO
                result.add(INVALID_TOWN);
            }
        }

        return String.join("\n", result);
    }
}
