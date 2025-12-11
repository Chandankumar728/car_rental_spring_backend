package com.carrentalprojects.Car_Rental_Spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carrentalprojects.Car_Rental_Spring.entity.Car;


@Repository

public interface CarRepository extends JpaRepository<Car, Long> {



}
