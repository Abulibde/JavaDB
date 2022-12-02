package com.example.modelmaplab.domain.DTO;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "addresses")
@XmlAccessorType(XmlAccessType.FIELD)
public class AddressXmlDTO {
@XmlAttribute(name = "id")
    private int id;


    private String country;


    private String city;

    public AddressXmlDTO(){}


    public AddressXmlDTO(int id, String country, String city) {
        this.id = id;
        this.country = country;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
