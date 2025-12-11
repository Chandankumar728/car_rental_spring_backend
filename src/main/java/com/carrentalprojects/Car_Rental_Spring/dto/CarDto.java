package com.carrentalprojects.Car_Rental_Spring.dto;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDto {

    private Long id;
    
    private String brand;
    
    private String type;
    
    private Date modalYear;
    
    private Long price;
    
    private String description;
    
    private String color;
    
    private String transmission;
    
    @JsonIgnore  // Exclude from JSON serialization (used for incoming multipart requests)
    private MultipartFile image;
    
    private byte[] returnImage;  // Fixed typo: retunnImage -> returnImage (used for outgoing responses)
    
}