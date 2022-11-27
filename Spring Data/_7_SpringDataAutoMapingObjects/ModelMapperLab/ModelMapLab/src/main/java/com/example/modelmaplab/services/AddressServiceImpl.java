package com.example.modelmaplab.services;

import com.example.modelmaplab.domain.DTO.CreateAddressDTO;
import com.example.modelmaplab.domain.entities.Address;
import com.example.modelmaplab.repositories.AddressRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {
    private AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }


    @Override
    public Address create(CreateAddressDTO data) {

        ModelMapper modelMapper = new ModelMapper();

        Address address = modelMapper.map(data, Address.class);

        return this.addressRepository.save(address);
    }
}
