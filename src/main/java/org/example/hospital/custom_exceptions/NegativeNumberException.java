package org.example.hospital.custom_exceptions;

public class NegativeNumberException extends Exception {
    public NegativeNumberException(String message) {
        super(message);
    }
}