package com.example.modelmaplab.services;

import com.example.modelmaplab.domain.DTO.CreateEmployeeDTO;
import com.example.modelmaplab.domain.DTO.EmployeeDTO;
import com.example.modelmaplab.domain.entities.Address;
import com.example.modelmaplab.domain.entities.Employee;
import com.example.modelmaplab.repositories.AddressRepository;
import com.example.modelmaplab.repositories.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {


    private final AddressRepository addressRepository;
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(AddressRepository addressRepository, EmployeeRepository employeeRepository) {
        this.addressRepository = addressRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    @Transactional
    public Employee create(CreateEmployeeDTO employeeDTO) {
        ModelMapper modelMapper = new ModelMapper();

        Employee employee = modelMapper.map(employeeDTO, Employee.class);

        Optional<Address> address = this.addressRepository.findByCountryAndCity(
                employeeDTO.getAddress().getCountry(),
                employeeDTO.getAddress().getCity());

        address.ifPresent(employee::setAddress);

        return this.employeeRepository.save(employee);
    }
}
