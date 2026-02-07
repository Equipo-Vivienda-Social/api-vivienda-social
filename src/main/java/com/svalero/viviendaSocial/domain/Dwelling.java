package com.svalero.viviendaSocial.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "dwellings")
public class Dwelling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String street;
    @Column
    private String city;
    @Column
    private String type;
    @Column
    private int room;
    @Column
    private boolean available;
    @Column
    private LocalDate buildDate;

    @JsonIgnoreProperties("dwelling")
    @OneToMany(mappedBy = "dwelling", cascade = CascadeType.REMOVE)
    private List<Applicant> applicants;
}
