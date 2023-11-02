package org.example;

import org.example.hospital.people.Patient;
import org.example.hospital.people.Position;
import org.example.hospital.pseudo_database.Db;

/**
 * Entry point
 */
public class Main {
    public static void main(String[] args) {
        // Creating pseudo database:
        Db.createDb();
        // Print doctor info before registration of a new patient:
        System.out.println(Db.therapeuticDept.getEmployee(Position.DOCTOR));
        // Creating unknown patient:
        Patient patient = new Patient();
        // Unknown patient goes to Hospital, register and so on:
        patient.goToHospital();
        // Print patient info:
        System.out.println(patient);
        // Print doctor info after registration of a new patient:
        System.out.println(patient.getDepartment().getEmployee(Position.DOCTOR));
    }
}