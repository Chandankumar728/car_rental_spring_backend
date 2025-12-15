package com.carrentalprojects.Car_Rental_Spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carrentalprojects.Car_Rental_Spring.dto.CarDto;
import com.carrentalprojects.Car_Rental_Spring.services.admin.AdminService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

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

        // GET - Get car by ID
    @GetMapping("/car/{id}")
    public ResponseEntity<CarDto> getCarById(@PathVariable Long id) {
        try {
            CarDto carDto = adminService.getCarById(id);
            
            if (carDto != null) {
                return ResponseEntity.ok(carDto);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            System.err.println("Error in getCarById controller: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // PUT - Update car
    @PutMapping(value = "/update-car/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, Object>> updateCar(
            @PathVariable Long id,
            @ModelAttribute CarDto carDto) {
        
        System.out.println("========== Controller Received PUT Request for ID: " + id + " ==========");
        
        Map<String, Object> response = new HashMap<>();
        
        try {
            boolean success = adminService.updateCar(id, carDto);
            
            if (success) {
                response.put("message", "Car updated successfully");
                response.put("success", true);
                return ResponseEntity.ok(response);
            } else {
                response.put("message", "Car not found with ID: " + id);
                response.put("success", false);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            System.err.println("Exception in updateCar controller: " + e.getMessage());
            e.printStackTrace();
            response.put("message", "Error: " + e.getMessage());
            response.put("success", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // DELETE - Delete car
    @DeleteMapping("/delete-car/{id}")
    public ResponseEntity<Map<String, Object>> deleteCar(@PathVariable Long id) {
        System.out.println("========== Controller Received DELETE Request for ID: " + id + " ==========");
        
        Map<String, Object> response = new HashMap<>();
        
        try {
            boolean success = adminService.deleteCar(id);
            
            if (success) {
                response.put("message", "Car deleted successfully");
                response.put("success", true);
                return ResponseEntity.ok(response);
            } else {
                response.put("message", "Car not found with ID: " + id);
                response.put("success", false);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            System.err.println("Exception in deleteCar controller: " + e.getMessage());
            e.printStackTrace();
            response.put("message", "Error: " + e.getMessage());
            response.put("success", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


}