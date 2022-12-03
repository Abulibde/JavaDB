package com.example.football.service.impl;

import com.example.football.models.dto.ImportStatDTO;
import com.example.football.models.dto.ImportStatRootDTO;
import com.example.football.models.entity.Stat;
import com.example.football.repository.StatRepository;
import com.example.football.service.StatService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

//ToDo - Implement all methods
@Service
public class StatServiceImpl implements StatService {

    private static final String INVALID_STAT = "Invalid Stat";
    private static final String SUCCESSFULLY_ADDED_STAT = "Successfully imported Stat %.2f - %.2f - %.2f";
    private final Path path =
            Path.of("src", "main", "resources", "files", "xml", "stats.xml");

    private final StatRepository statRepository;
    private final Unmarshaller unmarshaller;
    private final Validator validator;
    private final ModelMapper mapper;

    @Autowired
    public StatServiceImpl(StatRepository statRepository) throws JAXBException {
        this.statRepository = statRepository;

        JAXBContext context = JAXBContext.newInstance(ImportStatRootDTO.class);
        this.unmarshaller = context.createUnmarshaller();

        this.validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();

        this.mapper = new ModelMapper();

    }


    @Override
    public boolean areImported() {
        return this.statRepository.count() > 0;
    }

    @Override
    public String readStatsFileContent() throws IOException {
        //  return String.join("\n", Files.readAllLines(path));

        return Files.readString(path);
    }

    @Override
    public String importStats() throws FileNotFoundException, JAXBException {

        ImportStatRootDTO statDTOs = (ImportStatRootDTO)
                this.unmarshaller.unmarshal(new FileReader(path.toAbsolutePath().toString()));


        return statDTOs
                .getStats()
                .stream()
                .map(this::importStat)
                .collect(Collectors.joining("\n"));

    }

    private String importStat(ImportStatDTO dto) {

        Set<ConstraintViolation<ImportStatDTO>> errors = this.validator.validate(dto);

        if (!errors.isEmpty()) {
            return INVALID_STAT;
        }

        Optional<Stat> optStat = this.statRepository
                .findByShootingAndPassingAndEndurance
                        (dto.getShooting(), dto.getPassing(), dto.getEndurance());

        if (optStat.isPresent()) {
            return INVALID_STAT;
        }

        Stat stat = this.mapper.map(dto, Stat.class);
        this.statRepository.save(stat);

        return String.format(SUCCESSFULLY_ADDED_STAT,
                stat.getPassing(),
                stat.getShooting(),
                stat.getEndurance());
    }
}
