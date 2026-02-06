package com.svalero.viviendaSocial.repository;

import com.svalero.viviendaSocial.domain.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
    List<Applicant> findAll();

    List<Applicant> findByName(String name);

    List<Applicant> findBySurname(String surname);

    List<Applicant> findByDni(String dni);

    List<Applicant> findBySalary(int salary);

    List<Applicant> findByFamilyMembers(int familyMembers);

    List<Applicant> findByEmployed(boolean employed);

    List<Applicant> findByDwellingId(long dwellingId);
}
