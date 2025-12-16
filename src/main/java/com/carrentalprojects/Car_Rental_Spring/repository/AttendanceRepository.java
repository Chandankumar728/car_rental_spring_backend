package com.carrentalprojects.Car_Rental_Spring.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carrentalprojects.Car_Rental_Spring.entity.Attendance;
@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
boolean existsByEmployeeIdAndDate(Long employeeId, LocalDate date);
List<Attendance> findByEmployeeId(Long employeeId);
List<Attendance> findByDate(LocalDate date);
}
