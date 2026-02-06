package com.svalero.viviendaSocial.exception;

public class ApplicantNotFoundException extends RuntimeException {

    public ApplicantNotFoundException() {
        super("Applicant not found");
    }

    public ApplicantNotFoundException(Long id) {
        super("Applicant not found with ID: " + id);
    }

    public ApplicantNotFoundException(String message) {
        super(message);
    }
}
