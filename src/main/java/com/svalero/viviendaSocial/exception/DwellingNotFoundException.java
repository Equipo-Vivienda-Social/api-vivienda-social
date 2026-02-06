package com.svalero.viviendaSocial.exception;

public class DwellingNotFoundException extends RuntimeException {
    public DwellingNotFoundException() {
        super("Dwelling not found");
    }

    public DwellingNotFoundException(long id) {
        super("Dwelling with id " + id + " not found");
    }

    public DwellingNotFoundException(String message) {
        super(message);
    }
}
