package org.example.hospital.structure;

import org.example.hospital.people.Employee;
import org.example.hospital.people.Patient;

import java.util.ArrayList;

public final class Hospital implements IAddPatients, IGetEmployeesBySomething {
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

    public ArrayList<Patient> getPatients() {
        return patients;
    }

    @Override
    public ArrayList<Employee> getEmployeesBySpecialistClass(int specialistClass) {
        ArrayList<Employee> tempList = new ArrayList<>();
        for (Employee employee: employees) {
            if (employee.getPosition().getSpecialistClass() == specialistClass) {
                tempList.add(employee);
            }
        }
        return tempList;
    }

    private StringBuilder combineObjectTitles(Object[] objects) {
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
        return "Hospital '" + title + "' / Departments: " + combineObjectTitles(departments);
    }
}
