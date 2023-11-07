package org.example.hospital.custom_exceptions;

public class MenuItemNumberOutOfBoundsException extends Exception {
    public MenuItemNumberOutOfBoundsException(String message) {
        super(message);
    }
}