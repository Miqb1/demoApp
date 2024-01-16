package com.klaudiusz.demonstration.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDto {
    @Id
    @GeneratedValue
    private Long id;
    private String brand;
    private String model;
    private String color;
    private String prodYear;
    private String regNumber;
}

