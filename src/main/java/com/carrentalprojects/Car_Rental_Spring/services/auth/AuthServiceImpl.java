package com.carrentalprojects.Car_Rental_Spring.services.auth;

import org.springframework.stereotype.Service;

import com.carrentalprojects.Car_Rental_Spring.dto.SignUpRequest;
import com.carrentalprojects.Car_Rental_Spring.dto.UserDto;
import com.carrentalprojects.Car_Rental_Spring.entity.Users;
import com.carrentalprojects.Car_Rental_Spring.enums.UserRole;
import com.carrentalprojects.Car_Rental_Spring.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    @Override
    public UserDto createCustomer(SignUpRequest signUpRequest){
       Users user = new Users();
       user.setEmail(signUpRequest.getEmail());
       user.setName(signUpRequest.getName());
       user.setPassword(signUpRequest.getPassword());
       user.setUserRole(UserRole.CUSTOMER);
       Users createdUser = userRepository.save(user);
       UserDto userDto = new UserDto();
       userDto.setId(createdUser.getId());
       userDto.setEmail(createdUser.getEmail());
       userDto.setName(createdUser.getName());
       return userDto;
    }
}
