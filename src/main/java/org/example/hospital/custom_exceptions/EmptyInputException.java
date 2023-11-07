package org.example.hospital.custom_exceptions;

public class EmptyInputException extends Exception {
    public EmptyInputException(String message) {
        super(message);
    }
}