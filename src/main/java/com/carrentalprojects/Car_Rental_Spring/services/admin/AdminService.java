package com.carrentalprojects.Car_Rental_Spring.services.admin;

import java.util.List;

import com.carrentalprojects.Car_Rental_Spring.dto.CarDto;

public interface AdminService {

    boolean postCar (CarDto carDto);
    
     List<CarDto> getAllCars();
    

}
