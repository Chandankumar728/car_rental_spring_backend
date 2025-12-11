package com.carrentalprojects.Car_Rental_Spring.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Data
@Table(name="cars")
public class Car {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String type;
    private Date modalYear;
    private Long price;
    private String description;
    private String color;
    private String transmission;
    @Column(columnDefinition = "LONGBLOB")
    private byte[] image;
    
}
