package com.svalero.viviendaSocial.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicantOutDTO {
    private String name;
    private String surname;
    private String dni;
    private int salary;
    private int familyMembers;
    private boolean employed;
}
