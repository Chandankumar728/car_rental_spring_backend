package com.carrentalprojects.Car_Rental_Spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carrentalprojects.Car_Rental_Spring.entity.Employee;

public interface EmployeeRepository extends JpaRepository <Employee, Long> {

}
