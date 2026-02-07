package com.svalero.viviendaSocial.service;

import com.svalero.viviendaSocial.domain.Applicant;
import com.svalero.viviendaSocial.dto.ApplicantOutDTO;
import com.svalero.viviendaSocial.repository.ApplicantRepository;
import com.svalero.viviendaSocial.exception.ApplicantNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ApplicantService {

    @Autowired
    private ApplicantRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    public Applicant patch(Long id, Map<String, Object> updates) {
        Applicant applicant = findById(id);
        updates.forEach((key, value) -> {
            java.lang.reflect.Field field = org.springframework.util.ReflectionUtils.findField(Applicant.class, key);
            if (field != null) {
                field.setAccessible(true);
                org.springframework.util.ReflectionUtils.setField(field, applicant, value);
            }
        });
        return repository.save(applicant);
    }

    public List<ApplicantOutDTO> findAll(String name, String surname, String dni, Integer salary, Integer familyMembers,
            Boolean employed) {
        List<Applicant> allApplicants;

        if (name != null && !name.isEmpty()) {
            allApplicants = repository.findByName(name);
        } else if (surname != null && !surname.isEmpty()) {
            allApplicants = repository.findBySurname(surname);
        } else if (dni != null && !dni.isEmpty()) {
            allApplicants = repository.findByDni(dni);
        } else if (salary != null) {
            allApplicants = repository.findBySalary(salary);
        } else if (familyMembers != null) {
            allApplicants = repository.findByFamilyMembers(familyMembers);
        } else if (employed != null) {
            allApplicants = repository.findByEmployed(employed);
        } else {
            allApplicants = repository.findAll();
        }

        return modelMapper.map(allApplicants, new TypeToken<List<ApplicantOutDTO>>() {
        }.getType());
    }

    public Applicant findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ApplicantNotFoundException(id));
    }

    public List<Applicant> findAllApplicantsById(List<Long> ids) {
        return (List<Applicant>) repository.findAllById(ids);
    }

    public Applicant save(Applicant applicant) {
        return repository.save(applicant);
    }

    public Applicant update(Long id, Applicant details) {
        Applicant applicant = findById(id);
        applicant.setName(details.getName());
        applicant.setSurname(details.getSurname());
        applicant.setDni(details.getDni());
        applicant.setBirthDate(details.getBirthDate());
        applicant.setSalary(details.getSalary());
        applicant.setFamilyMembers(details.getFamilyMembers());
        applicant.setEmployed(details.isEmployed());
        applicant.setDwelling(details.getDwelling());
        return repository.save(applicant);
    }

    public void delete(Long id) {
        repository.delete(findById(id));
    }

    public com.svalero.viviendaSocial.domain.Dwelling getDwelling(long id) {
        Applicant applicant = findById(id);
        return applicant.getDwelling();
    }
}
