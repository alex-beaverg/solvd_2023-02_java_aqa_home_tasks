package org.example.hospital.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.hospital.people.*;
import org.example.hospital.structure.Department;
import org.example.hospital.structure.Hospital;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Creator {
    private final static Logger LOGGER_TO_CONSOLE_AND_FILE = LogManager.getLogger("Errors_To_Console_And_File");

    public static Hospital setHospital(String title) {
        return new Hospital(title);
    }

    public static Department setDepartment(String title, int officeNumber) {
        return new Department(title, officeNumber);
    }

    public static Employee setEmployee(String firstName, String lastName, int age, String city, String street,
                                       int houseNumber, int flatNumber, Department department, Position position,
                                       Schedule schedule) {
        Address address = setAddress(city, street, houseNumber, flatNumber);
        return new Employee(firstName, lastName, age, address, department, position, schedule);
    }

    public static Employee setEmployee(String firstName, String lastName, int age, Address address,
                                       Department department, Position position, Schedule schedule) {
        return new Employee(firstName, lastName, age, address, department, position, schedule);
    }

    public static Patient setPatient() {
        return new Patient();
    }

    public static Patient setPatient(String firstName, String lastName, int age, String city, String street,
                                     int houseNumber, int flatNumber, Diagnosis diagnosis, Department department) {
        Address address = setAddress(city, street, houseNumber, flatNumber);
        return new Patient(firstName, lastName, age, address, diagnosis, department);
    }

    public static Address setAddress() {
        return new Address();
    }

    public static Address setAddress(String city, String street, int houseNumber, int flatNumber) {
        return new Address(city, street, houseNumber, flatNumber);
    }

    public static Address setAddress(String path) {
        try (FileReader fileReader = new FileReader(path)) {
            Scanner scanner = new Scanner(fileReader);
            String city = scanner.nextLine();
            String street = scanner.nextLine();
            int houseNumber = Integer.parseInt(scanner.nextLine());
            int flatNumber = Integer.parseInt(scanner.nextLine());
            return new Address(city, street, houseNumber, flatNumber);
        } catch (IOException e) {
            LOGGER_TO_CONSOLE_AND_FILE.error("[IOException]: File not found!");
            return null;
        } catch (NumberFormatException e) {
            LOGGER_TO_CONSOLE_AND_FILE.error("[NumberFormatException]: Entered data is not a number!");
            return null;
        }
    }
}
