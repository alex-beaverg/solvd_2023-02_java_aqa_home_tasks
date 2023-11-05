package org.example.hospital.structure;

import org.example.hospital.people.Employee;
import org.example.hospital.people.Patient;

import java.util.ArrayList;

public class Hospital implements ICombineObjectTitles, IAddPatients {
    private final String title;
    private Department[] departments;
    private Employee[] employees;
    private final ArrayList<Patient> patients = new ArrayList<>();

    public Hospital(String title) {
        this.title = title;
    }

    public Department[] getDepartments() {
        return departments;
    }

    @Override
    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public void setDepartments(Department[] departments) {
        this.departments = departments;
    }

    public void setEmployees(Employee[] employees) {
        this.employees = employees;
    }

    public Employee[] getEmployees() {
        return employees;
    }

    public ArrayList<Patient> getPatients() {
        return patients;
    }

    @Override
    public StringBuilder combineObjectTitles(Object[] objects) {
        StringBuilder combiningObjectTitles = new StringBuilder();
        if (objects instanceof Department[] departs) {
            for (Department department: departs) {
                combiningObjectTitles.append("[").append(department.getTitle()).append("] ");
            }
        }
        return combiningObjectTitles;
    }

    @Override
    public String toString() {
        return "Hospital '" + title + "':" +
                "\n\tDepartments: " + combineObjectTitles(departments);
    }
}
