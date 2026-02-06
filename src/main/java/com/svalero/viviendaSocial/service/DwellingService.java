package com.svalero.viviendaSocial.service;

import com.svalero.viviendaSocial.domain.Dwelling;
import com.svalero.viviendaSocial.repository.DwellingRepository;
import com.svalero.viviendaSocial.exception.DwellingNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DwellingService {

    @Autowired
    private DwellingRepository repository;

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

    public List<Dwelling> findAll(String city, String type, Integer room, Boolean available) {
        Dwelling probe = new Dwelling();
        if (city != null)
            probe.setCity(city);
        if (type != null)
            probe.setType(type);
        if (room != null)
            probe.setRoom(room);
        if (available != null)
            probe.setAvailable(available);

        org.springframework.data.domain.ExampleMatcher matcher = org.springframework.data.domain.ExampleMatcher
                .matching()
                .withIgnoreNullValues()
                .withStringMatcher(org.springframework.data.domain.ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase();

        return repository.findAll(org.springframework.data.domain.Example.of(probe, matcher));
    }

    public Dwelling findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new DwellingNotFoundException(id));
    }

    public Dwelling save(Dwelling dwelling) {
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
}
