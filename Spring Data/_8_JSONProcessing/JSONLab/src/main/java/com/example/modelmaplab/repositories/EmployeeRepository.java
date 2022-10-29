package com.example.modelmaplab.repositories;

import com.example.modelmaplab.domain.DTO.EmployeeNamesDTO;
import com.example.modelmaplab.domain.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {


    @Query("SELECT new com.example.modelmaplab.domain.DTO.EmployeeNamesDTO (e.firstName, e.lastName)" +
            " FROM Employee AS e" +
            " WHERE e.id = :id")
    EmployeeNamesDTO findNamesById(long id);

}