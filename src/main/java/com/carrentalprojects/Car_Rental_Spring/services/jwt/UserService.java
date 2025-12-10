package com.carrentalprojects.Car_Rental_Spring.services.jwt;

import org.springframework.security.core.userdetails.UserDetailsService;
import com.carrentalprojects.Car_Rental_Spring.entity.Users;

public interface UserService extends UserDetailsService {
    Users findByEmail(String email);
}
