package com.carrentalprojects.Car_Rental_Spring.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.carrentalprojects.Car_Rental_Spring.dto.EmployeeDto;
import com.carrentalprojects.Car_Rental_Spring.services.employee.EmployeeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    // 1️. Add Employee
    @PostMapping
    public ResponseEntity<Map<String, Object>> addEmployee(
            @RequestBody EmployeeDto employeeDto) {

        boolean isAdded = employeeService.addEmployee(employeeDto);

        if (isAdded) {
            return ResponseEntity.ok(
                    Map.of("message", "Employee added successfully", "success", true)
            );
        }
        return ResponseEntity.status(500)
                .body(Map.of("message", "Failed to add employee", "success", false));
    }

    // 2️. Get all employees
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllEmployees() {
        return ResponseEntity.ok(
                Map.of("data", employeeService.getAllEmployees(), "success", true)
        );
    }

    // 3️. Get employee by ID
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getEmployeeById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                Map.of("data", employeeService.getEmployeeById(id), "success", true)
        );
    }

    // 4️. Update employee
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateEmployee(
            @PathVariable Long id,
            @RequestBody EmployeeDto employeeDto) {

        boolean isUpdated = employeeService.updateEmployee(id, employeeDto);

        if (isUpdated) {
            return ResponseEntity.ok(
                    Map.of("message", "Employee updated successfully", "success", true)
            );
        }
        return ResponseEntity.status(500)
                .body(Map.of("message", "Failed to update employee", "success", false));
    }

    // 5️. Delete employee
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteEmployee(
            @PathVariable Long id) {

        boolean isDeleted = employeeService.deleteEmployee(id);

        if (isDeleted) {
            return ResponseEntity.ok(
                    Map.of("message", "Employee deleted successfully", "success", true)
            );
        }
        return ResponseEntity.status(500)
                .body(Map.of("message", "Failed to delete employee", "success", false));
    }


}
