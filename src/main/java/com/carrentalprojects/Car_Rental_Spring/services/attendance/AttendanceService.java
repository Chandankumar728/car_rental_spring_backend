package com.carrentalprojects.Car_Rental_Spring.services.attendance;

import java.util.List;

import com.carrentalprojects.Car_Rental_Spring.dto.AttendanceDto;
import com.carrentalprojects.Car_Rental_Spring.entity.Attendance;

public interface AttendanceService {
    boolean markAttendance(AttendanceDto dto);
    List<Attendance> getAttendanceByEmployee(Long employeeId);
}
