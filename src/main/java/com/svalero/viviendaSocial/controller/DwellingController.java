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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/dwellings")
public class DwellingController {

    @Autowired
    private DwellingService dwellingService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<DwellingOutDTO> addDwelling(@Valid @RequestBody DwellingInDTO dwellingInDTO) {
        Dwelling dwelling = modelMapper.map(dwellingInDTO, Dwelling.class);
        Dwelling createdDwelling = dwellingService.save(dwelling);
        return new ResponseEntity<>(modelMapper.map(createdDwelling, DwellingOutDTO.class), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DwellingOutDTO>> getAllDwellings(
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Integer room,
            @RequestParam(required = false) Boolean available) {
        List<Dwelling> dwellings = dwellingService.findAll(city, type, room, available);
        List<DwellingOutDTO> dwellingsDTO = dwellings.stream()
                .map(dwelling -> modelMapper.map(dwelling, DwellingOutDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(dwellingsDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DwellingOutDTO> getDwellingById(@PathVariable long id) {
        Dwelling dwelling = dwellingService.findById(id);
        return new ResponseEntity<>(modelMapper.map(dwelling, DwellingOutDTO.class), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DwellingOutDTO> updateDwelling(
            @PathVariable long id,
            @Valid @RequestBody DwellingInDTO dwellingInDTO) {
        Dwelling dwellingDetails = modelMapper.map(dwellingInDTO, Dwelling.class);
        Dwelling updatedDwelling = dwellingService.update(id, dwellingDetails);
        return new ResponseEntity<>(modelMapper.map(updatedDwelling, DwellingOutDTO.class), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DwellingOutDTO> patchDwelling(
            @PathVariable long id,
            @RequestBody Map<String, Object> updates) {
        Dwelling updatedDwelling = dwellingService.patch(id, updates);
        return new ResponseEntity<>(modelMapper.map(updatedDwelling, DwellingOutDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDwelling(@PathVariable long id) {
        dwellingService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
