package com.svalero.viviendaSocial.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DwellingOutDTO {
    private String city;
    private String type;
    private int room;
    private boolean available;
}
