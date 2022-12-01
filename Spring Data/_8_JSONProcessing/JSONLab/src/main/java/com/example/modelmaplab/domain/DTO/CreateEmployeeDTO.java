package com.example.modelmaplab.domain.DTO;

import com.example.modelmaplab.domain.DTO.addresses.CreateAddressDTO;
import com.google.gson.annotations.Expose;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CreateEmployeeDTO {
    @Expose
    private String firstName;

    @Expose
    private String lastName;

    @Expose
    private BigDecimal salary;

    @Expose
    private LocalDate birthday;

    @Expose
    private CreateAddressDTO address;

    public CreateEmployeeDTO(String firstName, String lastName,
                             BigDecimal salary, LocalDate birthday, CreateAddressDTO address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.birthday = birthday;
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public CreateAddressDTO getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "CreateEmployeeDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                ", birthday=" + birthday +
                ", address=" + address +
                '}';
    }
}