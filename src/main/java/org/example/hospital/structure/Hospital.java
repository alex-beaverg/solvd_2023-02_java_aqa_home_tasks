package org.example.hospital.structure;

import org.example.hospital.people.Employee;
import org.example.hospital.people.Patient;

import java.util.ArrayList;

public class Hospital {
    private final String hospitalTitle;
    private Department[] departments;
    private Employee[] employees;
    private final ArrayList<Patient> patients = new ArrayList<>();

    public Hospital(String hospitalTitle) {
        this.hospitalTitle = hospitalTitle;
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public String getHospitalTitle() {
        return hospitalTitle;
    }

    public void setDepartments(Department[] departments) {
        this.departments = departments;
    }

    public void setEmployees(Employee[] employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Hospital '" + hospitalTitle + "'";
    }
}
