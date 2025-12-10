package com.carrentalprojects.Car_Rental_Spring.dto;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String email;
    private String password;
    
    public String getUsername() {
        return email;
    }
}
