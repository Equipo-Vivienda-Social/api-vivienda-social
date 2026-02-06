package com.svalero.viviendaSocial.domain;

import jakarta.persistence.*;

import java.time.LocalDate;

public class Applicant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String dni;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @Column
    private int salary;
    @Column(name = "family_members")
    private int familyMembers;
    @Column
    private boolean employed;

    @ManyToOne
    @JoinColumn(name = "applicant_id")
    private Dwelling dwelling;

}
