package com.svalero.viviendaSocial.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "vivenda")
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

    @OneToMany(mappedBy = "dwelling", cascade = CascadeType.REMOVE)
    @Column
    private List<Applicant> applicants;
}
