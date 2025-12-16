package com.carrentalprojects.Car_Rental_Spring.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceDto {
    private Long employeeId;
    private LocalDate date;
    private String status;
}
