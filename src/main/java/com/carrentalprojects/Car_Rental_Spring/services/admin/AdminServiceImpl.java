package com.carrentalprojects.Car_Rental_Spring.services.admin;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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


     @Override
    public List<CarDto> getAllCars() {
        try {
            List<Car> cars = carRepository.findAll();
            System.out.println("Found " + cars.size() + " cars in database");
            
            // return cars.stream().map(this::mapCarToDto).collect(Collectors.toList());
            return cars.stream().map(car -> {
                CarDto carDto = new CarDto();
                carDto.setId(car.getId());
                carDto.setBrand(car.getBrand());
                carDto.setType(car.getType());
                carDto.setModalYear(car.getModalYear());
                carDto.setPrice(car.getPrice());
                carDto.setDescription(car.getDescription());
                carDto.setColor(car.getColor());
                carDto.setTransmission(car.getTransmission());
                carDto.setReturnImage(car.getImage());
                return carDto;
            }).collect(Collectors.toList());
        } catch (Exception e) {
            System.err.println("Error in getAllCars: " + e.getMessage());
            e.printStackTrace();
            return List.of(); // Return empty list on error
        }
    }

    //get car by id
    @Override
    public CarDto getCarById (Long id) {
        try {
            Optional<Car> carOptional = carRepository.findById(id);
            
            if (carOptional.isPresent()) {
                System.out.println("Car found with ID: " + id);
                Car car = carOptional.get();
                CarDto carDto = new CarDto();
                carDto.setId(car.getId());
                carDto.setBrand(car.getBrand());
                carDto.setType(car.getType());
                carDto.setModalYear(car.getModalYear());
                carDto.setPrice(car.getPrice());
                carDto.setDescription(car.getDescription());
                carDto.setColor(car.getColor());
                carDto.setTransmission(car.getTransmission());
                carDto.setReturnImage(car.getImage());
                return carDto;
            } else {
                System.out.println("Car not found with ID: " + id);
                return null;
            }
        } catch (Exception e) {
            System.err.println("Error in getCarById: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional
    public boolean deleteCar(Long id) {
        try {
            Optional<Car> carOptional = carRepository.findById(id);
            
            if (carOptional.isPresent()) {
                carRepository.deleteById(id);
                System.out.println("✓ Car deleted successfully with ID: " + id);
                return true;
            } else {
                System.out.println("✗ Car not found with ID: " + id);
                return false;
            }
        } catch (Exception e) {
            System.err.println("Error in deleteCar: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    @Transactional
    public boolean updateCar(Long id, CarDto carDto) {
        try {
            Optional<Car> carOptional = carRepository.findById(id);
            
            if (carOptional.isPresent()) {
                Car existingCar = carOptional.get();
                
                // Update fields
                existingCar.setBrand(carDto.getBrand());
                existingCar.setType(carDto.getType());
                existingCar.setModalYear(carDto.getModalYear());
                existingCar.setPrice(carDto.getPrice());
                existingCar.setDescription(carDto.getDescription());
                existingCar.setColor(carDto.getColor());
                existingCar.setTransmission(carDto.getTransmission());
                
                // Update image only if new image is provided
                if (carDto.getImage() != null && !carDto.getImage().isEmpty()) {
                    byte[] imageBytes = carDto.getImage().getBytes();
                    existingCar.setImage(imageBytes);
                    System.out.println("Image updated: " + imageBytes.length + " bytes");
                }
                
                carRepository.save(existingCar);
                System.out.println("✓ Car updated successfully with ID: " + id);
                return true;
            } else {
                System.out.println("✗ Car not found with ID: " + id);
                return false;
            }
        } catch (Exception e) {
            System.err.println("Error in updateCar: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}