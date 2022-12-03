package com.example.football.service.impl;

import com.example.football.models.dto.ImportTeamDTO;
import com.example.football.models.entity.Team;
import com.example.football.models.entity.Town;
import com.example.football.repository.TeamRepository;
import com.example.football.repository.TownRepository;
import com.example.football.service.TeamService;
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
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

//ToDo - Implement all methods
@Service
public class TeamServiceImpl implements TeamService {

    private static final String INVALID_TEAM = "Invalid Team";
    private static final String SUCCESSFULLY_ADDED_TEAM = "Successfully imported Team %s - %d";

    private final TeamRepository teamRepository;

    private final Gson gson;
    private final Validator validator;
    private final ModelMapper mapper;
    private TownRepository townRepository;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository, TownRepository townRepository) {
        this.teamRepository = teamRepository;
        this.townRepository = townRepository;

        this.gson = new GsonBuilder().create();

        this.validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();

        this.mapper = new ModelMapper();
    }

    @Override
    public boolean areImported() {
        return this.teamRepository.count() > 0;
    }

    @Override
    public String readTeamsFileContent() throws IOException {
        Path path = Path.of("src", "main", "resources", "files", "json", "teams.json");

        //  return String.join("\n", Files.readAllLines(path));

        return Files.readString(path);
    }

    @Override
    public String importTeams() throws IOException {

        String json = readTeamsFileContent();

        ImportTeamDTO[] teamsDTOs = this.gson.fromJson(json, ImportTeamDTO[].class);

        return Arrays.stream(teamsDTOs)
                .map(this::importTeam)
                .collect(Collectors.joining("\n"));
    }

    private String importTeam(ImportTeamDTO dto) {

        Set<ConstraintViolation<ImportTeamDTO>> errors = this.validator.validate(dto);

        if (!errors.isEmpty()) {
            return INVALID_TEAM;
        }

        Optional<Team> optTeam = this.teamRepository.findByName(dto.getName());

        if (optTeam.isPresent()) {
            return INVALID_TEAM;
        }

        Team team = this.mapper.map(dto, Team.class);
        Optional<Town> town = this.townRepository.findByName(dto.getTownName());

        team.setTown(town.get());

        this.teamRepository.save(team);

        return String.format(SUCCESSFULLY_ADDED_TEAM, team.getName(), team.getTown().getPopulation());
    }
}
