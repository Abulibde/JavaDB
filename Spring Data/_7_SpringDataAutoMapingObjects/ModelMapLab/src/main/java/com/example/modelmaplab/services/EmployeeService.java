package com.example.modelmaplab.services;

import com.example.modelmaplab.domain.DTO.CreateEmployeeDTO;
import com.example.modelmaplab.domain.DTO.EmployeeDTO;
import com.example.modelmaplab.domain.entities.Employee;

import java.util.List;

public interface EmployeeService {
    Employee create(CreateEmployeeDTO employeeDTO);

    List<EmployeeDTO> findAll();
}