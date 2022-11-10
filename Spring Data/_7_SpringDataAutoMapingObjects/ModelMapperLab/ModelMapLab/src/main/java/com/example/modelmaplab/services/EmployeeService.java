package com.example.modelmaplab.services;

import com.example.modelmaplab.domain.DTO.CreateEmployeeDTO;
import com.example.modelmaplab.domain.entities.Employee;

public interface EmployeeService {
  Employee create(CreateEmployeeDTO employeeDTO);
}
