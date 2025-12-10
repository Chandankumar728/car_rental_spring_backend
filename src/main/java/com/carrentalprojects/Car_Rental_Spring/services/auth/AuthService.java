package com.carrentalprojects.Car_Rental_Spring.services.auth;

import com.carrentalprojects.Car_Rental_Spring.dto.SignUpRequest;
import com.carrentalprojects.Car_Rental_Spring.dto.UserDto;

public interface AuthService {

    UserDto createCustomer(SignUpRequest signUpRequest);

    boolean hasCustomerWithEmail(String email);
}
