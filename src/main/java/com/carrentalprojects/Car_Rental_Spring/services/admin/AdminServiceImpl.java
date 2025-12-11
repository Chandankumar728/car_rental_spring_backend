package com.carrentalprojects.Car_Rental_Spring.services.admin;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carrentalprojects.Car_Rental_Spring.dto.CarDto;
import com.carrentalprojects.Car_Rental_Spring.entity.Car;
import com.carrentalprojects.Car_Rental_Spring.repository.CarRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final CarRepository carRepository;

    @Override
    @Transactional  // Add this annotation
    public boolean postCar(CarDto carDto) {
        try {
            // Log incoming data
            System.out.println("========== Starting Car Save Operation ==========");
            System.out.println("Brand: " + carDto.getBrand());
            System.out.println("Type: " + carDto.getType());
            System.out.println("Modal Year: " + carDto.getModalYear());
            System.out.println("Price: " + carDto.getPrice());
            System.out.println("Description: " + carDto.getDescription());
            System.out.println("Color: " + carDto.getColor());
            System.out.println("Transmission: " + carDto.getTransmission());
            
            // Check image
            if (carDto.getImage() != null) {
                System.out.println("Image file name: " + carDto.getImage().getOriginalFilename());
                System.out.println("Image size: " + carDto.getImage().getSize() + " bytes");
            } else {
                System.out.println("ERROR: Image is NULL!");
            }
            
            Car car = new Car();
            car.setBrand(carDto.getBrand());
            car.setType(carDto.getType());
            car.setModalYear(carDto.getModalYear());
            car.setPrice(carDto.getPrice());
            car.setDescription(carDto.getDescription());
            car.setColor(carDto.getColor());
            car.setTransmission(carDto.getTransmission());
            
            // Handle image with null check
            if (carDto.getImage() != null && !carDto.getImage().isEmpty()) {
                byte[] imageBytes = carDto.getImage().getBytes();
                car.setImage(imageBytes);
                System.out.println("Image converted to byte array: " + imageBytes.length + " bytes");
            } else {
                System.out.println("WARNING: No image provided or image is empty");
            }

            // Save and verify
            Car savedCar = carRepository.save(car);
            
            if (savedCar != null && savedCar.getId() != null) {
                System.out.println("✓ Car saved successfully with ID: " + savedCar.getId());
                System.out.println("========== Save Operation Completed ==========");
                return true;
            } else {
                System.out.println("✗ Car save returned null or no ID generated");
                return false;
            }
            
        } catch (Exception e) {
            System.err.println("========== ERROR in postCar ==========");
            System.err.println("Error message: " + e.getMessage());
            System.err.println("Error type: " + e.getClass().getName());
            e.printStackTrace();
            System.err.println("=====================================");
            return false;
        }
    }
}