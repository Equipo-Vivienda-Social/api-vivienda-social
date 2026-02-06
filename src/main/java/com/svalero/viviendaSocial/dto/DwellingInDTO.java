package com.svalero.viviendaSocial.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DwellingInDTO {

    @NotBlank(message = "City is required")
    @Size(min = 2, max = 100, message = "City must be between 2 and 100 characters")
    private String city;

    @NotBlank(message = "Type is required")
    @Size(min = 2, max = 50, message = "Type must be between 2 and 50 characters")
    private String type;

    @Min(value = 1, message = "Number of rooms must be at least 1")
    @Max(value = 20, message = "Number of rooms cannot exceed 20")
    private int room;

    private boolean available;
}
