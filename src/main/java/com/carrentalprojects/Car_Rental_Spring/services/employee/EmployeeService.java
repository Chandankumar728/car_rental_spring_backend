package com.carrentalprojects.Car_Rental_Spring.services.employee;

import java.util.List;

import com.carrentalprojects.Car_Rental_Spring.dto.EmployeeDto;

public interface EmployeeService {
  
    List<EmployeeDto> getAllEmployees();
    EmployeeDto getEmployeeById(Long id);
    boolean addEmployee(EmployeeDto employeeDto);
    boolean updateEmployee(Long id, EmployeeDto employeeDto);
    boolean deleteEmployee(Long id);

}

