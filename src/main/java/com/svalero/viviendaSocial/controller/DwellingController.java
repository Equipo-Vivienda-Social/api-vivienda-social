package com.svalero.viviendaSocial.controller;

import com.svalero.viviendaSocial.dto.DwellingInDTO;
import com.svalero.viviendaSocial.dto.DwellingOutDTO;
import com.svalero.viviendaSocial.domain.Dwelling;
import com.svalero.viviendaSocial.service.DwellingService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dwellings")
public class DwellingController {

    @Autowired
    private DwellingService dwellingService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<Dwelling> addDwelling(@Valid @RequestBody Dwelling dwelling) {

        Dwelling createdDwelling = dwellingService.save(dwelling);
        return new ResponseEntity<>(createdDwelling, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DwellingOutDTO>> getAllDwellings(
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Integer room,
            @RequestParam(required = false) Boolean available) {
        List<DwellingOutDTO> allDwellings = dwellingService.findAll(city, type, room, available);

        return ResponseEntity.ok(allDwellings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dwelling> getDwellingById(@PathVariable long id) {
        Dwelling dwelling = dwellingService.findById(id);
        return new ResponseEntity<>(modelMapper.map(dwelling, Dwelling.class), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dwelling> updateDwelling(
            @PathVariable long id,
            @Valid @RequestBody DwellingInDTO dwellingInDTO) {
        Dwelling dwellingDetails = modelMapper.map(dwellingInDTO, Dwelling.class);
        Dwelling updatedDwelling = dwellingService.update(id, dwellingDetails);
        return new ResponseEntity<>(modelMapper.map(updatedDwelling, Dwelling.class), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Dwelling> patchDwelling(
            @PathVariable long id,
            @RequestBody Map<String, Object> updates) {
        Dwelling updatedDwelling = dwellingService.patch(id, updates);
        return new ResponseEntity<>(modelMapper.map(updatedDwelling, Dwelling.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDwelling(@PathVariable long id) {
        dwellingService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
