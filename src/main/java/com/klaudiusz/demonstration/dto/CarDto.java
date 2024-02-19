package com.klaudiusz.demonstration.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDto {
    // DTO for Car model.
    private Long id;
    private String brand;
    private String model;
    private String color;
    private String prodYear;
    private String regNumber;

}
