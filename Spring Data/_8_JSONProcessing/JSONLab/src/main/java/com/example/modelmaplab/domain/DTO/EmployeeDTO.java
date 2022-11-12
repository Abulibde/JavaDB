package com.example.modelmaplab.domain.DTO;

import java.math.BigDecimal;

public class EmployeeDTO {

    //Create EmployeeDto class that will keep synthesized information about instances of the Employee class
    //(only first name, last name and salary)

    private String FirstName;

    private BigDecimal salary;

    private String addressCity;

    public EmployeeDTO(){}

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    @Override
    public String toString() {
        return "EmployeeDto{" +
                "FirstName='" + FirstName + '\'' +
                ", salary=" + salary +
                ", city='" + addressCity + '\'' +
                '}';
    }
}