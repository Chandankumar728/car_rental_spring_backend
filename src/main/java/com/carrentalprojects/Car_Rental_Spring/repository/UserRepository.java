package com.carrentalprojects.Car_Rental_Spring.repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carrentalprojects.Car_Rental_Spring.entity.Users;
import com.carrentalprojects.Car_Rental_Spring.enums.UserRole;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findFirstByEmail(String email);
    Users findFirstByUserRole(UserRole userRole);
}
