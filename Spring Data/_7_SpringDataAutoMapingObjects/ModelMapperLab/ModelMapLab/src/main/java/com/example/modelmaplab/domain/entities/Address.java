package com.example.modelmaplab.domain.entities;

public class Address {

    private long id;

    private String country;

    private String city;

    public Address() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public Address(String country, String city) {
        this.country = country;
        this.city = city;
    }
}
