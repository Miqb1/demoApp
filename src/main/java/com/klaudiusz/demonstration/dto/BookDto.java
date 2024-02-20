package com.klaudiusz.demonstration.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class BookDto {
    // DTO for Book model
    private Long id;
    private String name;
}