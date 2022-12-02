package com.example.modelmaplab.services;

import com.example.modelmaplab.domain.DTO.CreateAddressDTO;
import com.example.modelmaplab.domain.entities.Address;

public interface AddressService {
    Address create(CreateAddressDTO data);

}