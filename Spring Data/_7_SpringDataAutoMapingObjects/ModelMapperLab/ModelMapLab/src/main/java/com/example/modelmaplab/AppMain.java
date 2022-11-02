package com.example.modelmaplab;

import com.example.modelmaplab.domain.DTO.CreateAddressDTO;
import com.example.modelmaplab.domain.DTO.CreateEmployeeDTO;
import com.example.modelmaplab.domain.entities.Address;
import com.example.modelmaplab.services.AddressService;
import com.example.modelmaplab.services.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

@Component
public class AppMain implements CommandLineRunner {


    private final AddressService addressService;
    private final EmployeeService employeeService;
    private final Scanner scanner;

    @Autowired
    public AppMain(AddressService addressService, EmployeeService employeeService, ModelMapper modelMapper, Scanner scanner) {
        this.addressService = addressService;
        this.employeeService = employeeService;
        this.scanner = scanner;
    }


    @Override
    public void run(String... args) throws Exception {

        printAllEmployees();

//        createEmployee(scanner);

//        createAddress(scanner);


    }

    private void printAllEmployees() {
        this.employeeService.findAll()
                .forEach(System.out::println);
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
        String country = scanner.nextLine();
        String city = scanner.nextLine();

        CreateAddressDTO data = new CreateAddressDTO(country, city);

        Address address = addressService.create(data);

        System.out.println(address);
    }
}
