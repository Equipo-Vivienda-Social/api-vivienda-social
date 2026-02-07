package com.svalero.viviendaSocial.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DwellingInDto {

    private long id;
    private String street;
    private String city;
    private String type;
    private int room;
    private boolean available;
    private LocalDate buildDate;
    private List<Long> applicantsIds;
}
