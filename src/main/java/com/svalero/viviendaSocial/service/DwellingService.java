package com.svalero.viviendaSocial.service;

import com.svalero.viviendaSocial.domain.Applicant;
import com.svalero.viviendaSocial.domain.Dwelling;
import com.svalero.viviendaSocial.dto.DwellingInDto;
import com.svalero.viviendaSocial.dto.DwellingOutDTO;
import com.svalero.viviendaSocial.repository.DwellingRepository;
import com.svalero.viviendaSocial.exception.DwellingNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DwellingService {

    @Autowired
    private DwellingRepository repository;
    @Autowired
    private com.svalero.viviendaSocial.repository.ApplicantRepository applicantRepository;
    @Autowired
    private ModelMapper modelMapper;

    public Dwelling patch(Long id, java.util.Map<String, Object> updates) {
        Dwelling dwelling = findById(id);
        updates.forEach((key, value) -> {
            java.lang.reflect.Field field = org.springframework.util.ReflectionUtils.findField(Dwelling.class, key);
            if (field != null) {
                field.setAccessible(true);
                org.springframework.util.ReflectionUtils.setField(field, dwelling, value);
            }
        });
        return repository.save(dwelling);
    }

    public List<DwellingOutDTO> findAll(String city, Integer room, Boolean available) {
        List<Dwelling> allDwellings;

         if (available != null && available) {
            allDwellings = repository.findByAvailableTrue();
        } else if (city != null && !city.isEmpty()) {
            allDwellings = repository.findByCity(city);
        } else if (room != null) {
            allDwellings = repository.findByRoom(room);
        } else {
            allDwellings = repository.findAll();
        }

        return modelMapper.map(allDwellings, new TypeToken<List<DwellingOutDTO>>() {
        }.getType());
    }

    public Dwelling findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new DwellingNotFoundException(id));
    }

    public Dwelling save(DwellingInDto dwellingInDto, List<Applicant> applicants) {
        Dwelling dwelling = new Dwelling();
        dwelling.setApplicants(applicants);

        modelMapper.map(dwellingInDto, dwelling);
        return repository.save(dwelling);
    }

    public Dwelling update(Long id, Dwelling details) {
        Dwelling dwelling = findById(id);
        dwelling.setStreet(details.getStreet());
        dwelling.setCity(details.getCity());
        dwelling.setType(details.getType());
        dwelling.setRoom(details.getRoom());
        dwelling.setAvailable(details.isAvailable());
        dwelling.setBuildDate(details.getBuildDate());
        return repository.save(dwelling);
    }

    public void delete(Long id) {
        repository.delete(findById(id));
    }

    public List<com.svalero.viviendaSocial.domain.Applicant> getApplicants(long id) {
        findById(id); // Ensure dwelling exists
        return applicantRepository.findByDwellingId(id);
    }
}
