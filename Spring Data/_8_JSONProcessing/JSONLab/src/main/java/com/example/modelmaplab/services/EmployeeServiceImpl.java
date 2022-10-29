package com.example.modelmaplab.services;

import com.example.modelmaplab.domain.DTO.CreateEmployeeDTO;
import com.example.modelmaplab.domain.DTO.EmployeeDTO;
import com.example.modelmaplab.domain.DTO.EmployeeNamesDTO;
import com.example.modelmaplab.domain.entities.Address;
import com.example.modelmaplab.domain.entities.Employee;
import com.example.modelmaplab.repositories.AddressRepository;
import com.example.modelmaplab.repositories.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {


    private final AddressRepository addressRepository;
    private final EmployeeRepository employeeRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public EmployeeServiceImpl(AddressRepository addressRepository, EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.addressRepository = addressRepository;
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public Employee create(CreateEmployeeDTO employeeDTO) {

        Employee employee = modelMapper.map(employeeDTO, Employee.class);

        Optional<Address> address = this.addressRepository.findByCountryAndCity(
                employeeDTO.getAddress().getCountry(),
                employeeDTO.getAddress().getCity());

        address.ifPresent(employee::setAddress);

        return this.employeeRepository.save(employee);
    }

    @Override
    public List<EmployeeDTO> findAll() {
        List<Employee> listDTO = this.employeeRepository.findAll();

        return listDTO.stream()
                .map(employee -> modelMapper.map(employee, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeNamesDTO findNamesById(long id) {
        return this.employeeRepository.findNamesById(id);
    }
}