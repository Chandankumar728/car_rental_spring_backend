package com.carrentalprojects.Car_Rental_Spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carrentalprojects.Car_Rental_Spring.dto.CarDto;
import com.carrentalprojects.Car_Rental_Spring.services.admin.AdminService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;
//create car posting api
    @PostMapping(value = "/postcar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, Object>> postCar(@ModelAttribute CarDto carDto) {
        System.out.println("========== Controller Received Request ==========");
        System.out.println("CarDto received: " + carDto);
        
        Map<String, Object> response = new HashMap<>();
        
        try {
            boolean success = adminService.postCar(carDto);
            
            if (success) {
                response.put("message", "Car posted successfully");
                response.put("success", true);
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            } else {
                response.put("message", "Failed to post car - service returned false");
                response.put("success", false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        } catch (Exception e) {
            System.err.println("Exception in controller: " + e.getMessage());
            e.printStackTrace();
            response.put("message", "Error: " + e.getMessage());
            response.put("success", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    //get all car post list api

   // GET - Get all cars
    @GetMapping("/get-all-cars")
    public ResponseEntity<List<CarDto>> getAllCars() {
        try {
            List<CarDto> cars = adminService.getAllCars();
            return ResponseEntity.ok().body(cars);
        } catch (Exception e) {
            System.err.println("Error in getAllCars controller: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}