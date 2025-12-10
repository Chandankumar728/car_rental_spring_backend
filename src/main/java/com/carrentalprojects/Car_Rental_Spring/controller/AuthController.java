package com.carrentalprojects.Car_Rental_Spring.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import com.carrentalprojects.Car_Rental_Spring.dto.AuthenticationRequest;
import com.carrentalprojects.Car_Rental_Spring.dto.AuthenticationResponse;
import com.carrentalprojects.Car_Rental_Spring.dto.SignUpRequest;
import com.carrentalprojects.Car_Rental_Spring.dto.UserDto;
import com.carrentalprojects.Car_Rental_Spring.entity.Users;
import com.carrentalprojects.Car_Rental_Spring.repository.UserRepository;
import com.carrentalprojects.Car_Rental_Spring.services.auth.AuthService;
import com.carrentalprojects.Car_Rental_Spring.services.jwt.UserService;
import com.carrentalprojects.Car_Rental_Spring.utils.JwtUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> registerCustomer(@RequestBody SignUpRequest signUpRequest) {

        if (authService.hasCustomerWithEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST);
        }

        UserDto createdCustomerDto = authService.createCustomer(signUpRequest);

        if (createdCustomerDto == null) {
            return ResponseEntity.badRequest().body("Customer not created, try again later");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomerDto);
    }

    @PostMapping("/login")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
            throws BadCredentialsException, DisabledException, UsernameNotFoundException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password.");
        }

        final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getUsername());
        Optional<Users> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername());
        final String jwt = jwtUtil.generateToken(authenticationRequest.getUsername());

        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        if (optionalUser.isPresent()) {
            authenticationResponse.setJwt(jwt);
            authenticationResponse.setUserId(optionalUser.get().getId());
            authenticationResponse.setUserRole(optionalUser.get().getUserRole());
        }

        return authenticationResponse;
    }

}
