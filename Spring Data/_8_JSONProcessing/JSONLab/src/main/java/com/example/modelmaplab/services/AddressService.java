package com.example.modelmaplab.services;

import com.example.modelmaplab.domain.DTO.addresses.AddressDTO;
import com.example.modelmaplab.domain.DTO.addresses.CreateAddressDTO;
import com.example.modelmaplab.domain.entities.Address;

public interface AddressService {
    AddressDTO create(CreateAddressDTO data);

}