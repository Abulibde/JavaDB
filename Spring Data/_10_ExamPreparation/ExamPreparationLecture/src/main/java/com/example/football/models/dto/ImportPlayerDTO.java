package com.example.football.models.dto;


import com.example.football.models.entity.PlayerPosition;
import com.example.football.util.LocalDateAdapter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;



@XmlAccessorType(XmlAccessType.FIELD)
public class ImportPlayerDTO {

    @XmlElement(name = "first-name")
    @Size(min = 2)
    private String firstName;

    @XmlElement(name = "last-name")
    @Size(min = 2)
    private String lastName;

    @XmlElement
    @Email
    private String email;

    //dd/MM/yyyy
    @XmlElement(name = "birth-date")
    private String birthDate;

    @XmlElement
    private PlayerPosition position;

    @XmlElement(name = "town")
    private NameDTO town;

    @XmlElement(name = "town")
    private NameDTO team;

    @XmlElement(name = "stat")
    private StatsIdDTO stat;

    public ImportPlayerDTO(){}

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public PlayerPosition getPosition() {
        return position;
    }

    public NameDTO getTown() {
        return town;
    }

    public NameDTO getTeam() {
        return team;
    }

    public StatsIdDTO getStat() {
        return stat;
    }
}
