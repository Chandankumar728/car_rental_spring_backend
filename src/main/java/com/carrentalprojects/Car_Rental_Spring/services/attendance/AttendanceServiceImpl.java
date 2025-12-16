package com.carrentalprojects.Car_Rental_Spring.services.attendance;

import java.util.List;

import org.springframework.stereotype.Service;

import com.carrentalprojects.Car_Rental_Spring.dto.AttendanceDto;
import com.carrentalprojects.Car_Rental_Spring.entity.Attendance;
import com.carrentalprojects.Car_Rental_Spring.entity.AttendanceStatus;
// import com.carrentalprojects.Car_Rental_Spring.entity.AttendanceStatus;
import com.carrentalprojects.Car_Rental_Spring.entity.Employee;
import com.carrentalprojects.Car_Rental_Spring.repository.AttendanceRepository;
import com.carrentalprojects.Car_Rental_Spring.repository.EmployeeRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final EmployeeRepository employeeRepository;

    public boolean markAttendance(AttendanceDto dto) {

        if (attendanceRepository.existsByEmployeeIdAndDate(dto.getEmployeeId(), dto.getDate())) {
            return false; // Prevent duplicate attendance
        }

        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Attendance attendance = new Attendance();
        attendance.setEmployee(employee);
        attendance.setDate(dto.getDate());
        attendance.setStatus(AttendanceStatus.valueOf(dto.getStatus()));

        attendanceRepository.save(attendance);
        return true;
    }
//get all attendance by employee id
    @Override
    public List<Attendance> getAttendanceByEmployee(Long employeeId) {
        return attendanceRepository.findByEmployeeId(employeeId);
    }


    

}
