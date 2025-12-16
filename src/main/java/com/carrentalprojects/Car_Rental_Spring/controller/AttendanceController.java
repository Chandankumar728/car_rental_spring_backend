package com.carrentalprojects.Car_Rental_Spring.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.carrentalprojects.Car_Rental_Spring.dto.AttendanceDto;
import com.carrentalprojects.Car_Rental_Spring.entity.Attendance;
import com.carrentalprojects.Car_Rental_Spring.services.attendance.AttendanceService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    // 1️. Mark Attendance
    @PostMapping
    public ResponseEntity<Map<String, Object>> markAttendance(@RequestBody AttendanceDto attendanceDto) {
        boolean isMarked = attendanceService.markAttendance(attendanceDto);
        if (isMarked) {
            return ResponseEntity.ok(Map.of(
                "message", "Attendance marked successfully",
                "success", true
            ));
        } else {
            return ResponseEntity.badRequest().body(Map.of(
                "message", "Attendance already marked for this employee today",
                "success", false
            ));
        }
    }

    // 2️. Get Attendance by Employee ID
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<Map<String, Object>> getAttendanceByEmployee(@PathVariable Long employeeId) {
        List<Attendance> attendanceList = attendanceService.getAttendanceByEmployee(employeeId);
        return ResponseEntity.ok(Map.of(
            "data", attendanceList,
            "success", true
        ));
    }

    
    

    
}
