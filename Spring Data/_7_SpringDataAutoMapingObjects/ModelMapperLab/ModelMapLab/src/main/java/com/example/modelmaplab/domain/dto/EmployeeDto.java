package com.example.modelmaplab.domain.dto;

import java.math.BigDecimal;

public class EmployeeDto {

    //Create EmployeeDto class that will keep synthesized information about instances of the Employee class
    //(only first name, last name and salary)

    private String FirstName;

    private BigDecimal salary;

    private String city;

    public EmployeeDto(){}

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "EmployeeDto{" +
                "FirstName='" + FirstName + '\'' +
                ", salary=" + salary +
                ", city='" + city + '\'' +
                '}';
    }
}
