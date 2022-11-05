package com.example.modelmaplab;

import com.example.modelmaplab.domain.DTO.EmployeeDTO;
import com.example.modelmaplab.domain.entities.Address;
import com.example.modelmaplab.domain.entities.Employee;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.boot.CommandLineRunner;

import java.math.BigDecimal;

//@Component
public class ModelMapperMain implements CommandLineRunner {
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

        TypeMap<Employee, EmployeeDTO> typeMap = mapper.createTypeMap(Employee.class, EmployeeDTO.class);
        typeMap.addMapping(employee -> employee.getAddress().getCity(), EmployeeDTO::setAddressCity);

        Address address = new Address("Bulgaria", "Sofia");

        Employee employee = new Employee("testName", BigDecimal.TEN, address);


        EmployeeDTO employeeDtoTest = typeMap.map(employee);

        System.out.println(employeeDtoTest);


    }
}