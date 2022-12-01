package com.example.modelmaplab;

import com.example.modelmaplab.domain.DTO.addresses.AddressDTO;
import com.example.modelmaplab.domain.DTO.addresses.CreateAddressDTO;
import com.example.modelmaplab.domain.DTO.CreateEmployeeDTO;
import com.example.modelmaplab.domain.DTO.EmployeeDTO;
import com.example.modelmaplab.domain.entities.Address;
import com.example.modelmaplab.services.AddressService;
import com.example.modelmaplab.services.EmployeeService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

@Component
public class AppMain implements CommandLineRunner {


    private final AddressService addressService;
    private final EmployeeService employeeService;
    private final Scanner scanner;

    private final Gson gson;

    @Autowired
    public AppMain(AddressService addressService, EmployeeService employeeService, ModelMapper modelMapper, Scanner scanner, Gson gson) {
        this.addressService = addressService;
        this.employeeService = employeeService;
        this.scanner = scanner;
        this.gson = gson;
    }


    @Override
    public void run(String... args) throws Exception {

//        printEmployeeNames();


//        printAllEmployees();

//        createEmployee(scanner);

        createAddress();


    }

    private void printEmployeeNames() {
        System.out.println(this.employeeService.findNamesById(1L));
    }

    private void printAllEmployees() {
        List<EmployeeDTO> listEDTO = this.employeeService.findAll();

        listEDTO.forEach(System.out::println);
    }

    private void createEmployee() {
        String firstName = scanner.nextLine();
        BigDecimal salary = new BigDecimal(scanner.nextLine());
        LocalDate birthday = LocalDate.parse(scanner.nextLine());

//        long addressId = Long.parseLong(scanner.nextLine());

        String country = scanner.nextLine();
        String city = scanner.nextLine();

        CreateAddressDTO address = new CreateAddressDTO(country, city);

        CreateEmployeeDTO employeeDTO = new CreateEmployeeDTO
                (firstName, null, salary, birthday, address);

        this.employeeService.create(employeeDTO);

        System.out.println(employeeDTO);
    }

    private void createAddress() {

        String input = this.scanner.nextLine();
        CreateAddressDTO data = this.gson.fromJson(input, CreateAddressDTO.class);

        AddressDTO created = addressService.create(data);

        System.out.println(this.gson.toJson(created));

        System.out.println();
    }
}