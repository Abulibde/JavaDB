package com.example.modelmaplab.services;

import com.example.modelmaplab.domain.DTO.CreateAddressDTO;
import com.example.modelmaplab.domain.entities.Address;
import com.example.modelmaplab.repositories.AddressRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository, ModelMapper modelMapper) {
        this.addressRepository = addressRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public Address create(CreateAddressDTO data) {

        Address address = modelMapper.map(data, Address.class);

        return this.addressRepository.save(address);
    }
}
