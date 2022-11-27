package com.example.modelmaplab;

import com.example.modelmaplab.domain.dto.EmployeeDto;
import com.example.modelmaplab.domain.entities.Address;
import com.example.modelmaplab.domain.entities.Employee;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Main implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {

        ModelMapper mapper = new ModelMapper();

        /*
        PropertyMap<Employee,EmployeeDto> propertyMap = new PropertyMap<Employee, EmployeeDto>() {
            @Override
            protected void configure() {
                map().setCity(source.getAddress().getCity());
            }
        };
        mapper.addMappings(propertyMap);
         */

        TypeMap<Employee, EmployeeDto> typeMap = mapper.createTypeMap(Employee.class, EmployeeDto.class);
        typeMap.addMapping(employee -> employee.getAddress().getCity(), EmployeeDto::setCity);

        Address address = new Address("Bulgaria", "Sofia");

        Employee employeeTest = new Employee("testName", BigDecimal.TEN, address);


        EmployeeDto employeeDtoTest = typeMap.map(employeeTest);

        System.out.println(employeeDtoTest);


    }
}
