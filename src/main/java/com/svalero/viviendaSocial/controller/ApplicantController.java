package com.svalero.viviendaSocial.controller;

import com.svalero.viviendaSocial.domain.Applicant;
import com.svalero.viviendaSocial.dto.ApplicantOutDTO;
import com.svalero.viviendaSocial.service.ApplicantService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/applicants")
public class ApplicantController {

    @Autowired
    private ApplicantService applicantService;

    @Autowired
    private ModelMapper modelMapper;

    private final Logger logger = LoggerFactory.getLogger(ApplicantController.class);

    @PostMapping
    public ResponseEntity<Applicant> addApplicant(@Valid @RequestBody Applicant applicant) {
        Applicant createdApplicant = applicantService.save(applicant);
        return new ResponseEntity<>(createdApplicant, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ApplicantOutDTO>> getAllApplicants(

            @RequestParam(value = "salary",required = false) Integer salary,
            @RequestParam(value = "familyMembers",required = false) Integer familyMembers,
            @RequestParam(value = "employed" ,required = false) Boolean employed) {
        logger.info("GET /applicants");
        List<ApplicantOutDTO> allApplicants = applicantService.findAll(salary, familyMembers, employed);
        return ResponseEntity.ok(allApplicants);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Applicant> getApplicantById(@PathVariable long id) {
        Applicant applicant = applicantService.findById(id);
        return new ResponseEntity<>(modelMapper.map(applicant, Applicant.class), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Applicant> updateApplicant(
            @PathVariable long id,
            @Valid @RequestBody Applicant applicant) {
        Applicant updatedApplicant = applicantService.update(id, applicant);
        return new ResponseEntity<>(modelMapper.map(updatedApplicant, Applicant.class), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Applicant> patchApplicant(
            @PathVariable long id,
            @RequestBody Map<String, Object> updates) {
        Applicant updatedApplicant = applicantService.patch(id, updates);
        return new ResponseEntity<>(modelMapper.map(updatedApplicant, Applicant.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplicant(@PathVariable long id) {
        applicantService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}/dwelling")
    public ResponseEntity<com.svalero.viviendaSocial.domain.Dwelling> getDwelling(@PathVariable long id) {
        com.svalero.viviendaSocial.domain.Dwelling dwelling = applicantService.getDwelling(id);
        return ResponseEntity.ok(dwelling);
    }
}
