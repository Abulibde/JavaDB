package com.example.modelmaplab.domain.DTO.addresses;

public class CreateAddressDTO {

    private String country;

    private String city;

    public CreateAddressDTO(){}

    public CreateAddressDTO(String country, String city) {
        this.country = country;
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "CreateAddressDTO{" +
                "country='" + country + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}