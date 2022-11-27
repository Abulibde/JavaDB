package com.example.modelmaplab;

import com.example.modelmaplab.domain.DTO.CreateAddressDTO;
import com.example.modelmaplab.domain.entities.Address;
import com.example.modelmaplab.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AppMain implements CommandLineRunner {


    private final AddressService addressService;

    @Autowired
    public AppMain(AddressService addressService) {
        this.addressService = addressService;
    }


    @Override
    public void run(String... args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        String country = scanner.nextLine();
        String city = scanner.nextLine();

        CreateAddressDTO data = new CreateAddressDTO(country, city);

        Address address = addressService.create(data);

        System.out.println(address);


    }
}
