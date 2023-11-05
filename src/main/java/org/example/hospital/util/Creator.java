package org.example.hospital.util;

import org.example.hospital.people.*;
import org.example.hospital.structure.Department;
import org.example.hospital.structure.Hospital;

public class Creator {

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
}
