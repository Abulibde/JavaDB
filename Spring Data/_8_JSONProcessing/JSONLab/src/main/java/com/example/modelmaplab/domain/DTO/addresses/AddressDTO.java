package com.example.modelmaplab.domain.DTO.addresses;

public class AddressDTO extends CreateAddressDTO {

    private long id;

    public AddressDTO(String country, String city) {
        super(country, city);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
