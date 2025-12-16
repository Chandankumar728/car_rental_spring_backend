package com.carrentalprojects.Car_Rental_Spring.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

    private Long id;
    private String name;
    private String email;
    private String position;
    private String phoneNumber;
    private String address;
    private LocalDate hireDate;
}
