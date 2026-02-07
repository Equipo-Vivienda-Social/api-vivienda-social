package com.svalero.viviendaSocial.repository;

import com.svalero.viviendaSocial.domain.Dwelling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DwellingRepository extends JpaRepository<Dwelling, Long> {
    List<Dwelling> findByCity(String city);
    List<Dwelling> findAll();
    List<Dwelling> findByAvailableTrue();
    List<Dwelling> findByRoom(int room);
    List<Dwelling> findByAvailableFalse();
}
