package com.example.modelmaplab;

import com.example.modelmaplab.domain.dto.EmployeeDto;
import com.example.modelmaplab.domain.entities.Address;
import com.example.modelmaplab.domain.entities.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Main implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {


        ModelMapper mapper = new ModelMapper();

        Address address = new Address("Bulgaria", "Sofia");

        Employee employeeTest = new Employee("testName", BigDecimal.TEN, address);


        EmployeeDto employeeDtoTest = mapper.map(employeeTest, EmployeeDto.class);

        System.out.println(employeeDtoTest);


    }
}
