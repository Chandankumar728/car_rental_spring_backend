package com.carrentalprojects.Car_Rental_Spring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.carrentalprojects.Car_Rental_Spring.dto.SignUpRequest;
import com.carrentalprojects.Car_Rental_Spring.dto.UserDto;
import com.carrentalprojects.Car_Rental_Spring.services.auth.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> registerCustomer(@RequestBody SignUpRequest signUpRequest) {

        UserDto createdCustomerDto = authService.createCustomer(signUpRequest);

        if (createdCustomerDto == null) {
            return ResponseEntity.badRequest().body("Customer not created, try again later");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomerDto);
    }
}
