package com.solvd.hospital_project.hospital.custom_exceptions;

public class MenuItemOutOfBoundsException extends Exception {
    public MenuItemOutOfBoundsException(String message) {
        super(message);
    }
}