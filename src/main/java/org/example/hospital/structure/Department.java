package org.example.hospital.structure;

import org.example.hospital.people.Employee;
import org.example.hospital.people.Patient;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Department implements ICombineObjectTitles, IAddPatients {
    private final String title;
    private final int officeRoom;
    private Employee[] employees;
    private final ArrayList<Patient> patients = new ArrayList<>();

    public Department(String title,
                      int officeRoom) {
        this.title = title;
        this.officeRoom = officeRoom;
    }

    @Override
    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public String getTitle() {
        return title;
    }

    public int getOfficeRoom() {
        return officeRoom;
    }

    public Employee[] getEmployees() {
        return employees;
    }

    public void setEmployees(Employee[] employees) {
        this.employees = employees;
    }

    public Employee getEmployeeBySpecialistClass(int specialistClass) {
        ArrayList<Employee> tempList = new ArrayList<>();
        for (Employee employee: employees) {
            if (employee.getPosition().getSpecialistClass() == specialistClass) {
                tempList.add(employee);
            }
        }
        Random random = new Random();
        return tempList.get(random.nextInt(tempList.size()));
    }

    @Override
    public StringBuilder combineObjectTitles(Object[] objects) {
        StringBuilder combiningObjectTitles = new StringBuilder();
        if (objects instanceof Employee[] empls) {
            for (Employee employee: empls) {
                combiningObjectTitles.append("[").append(employee.getFirstName()).append(" ").append(employee.getLastName()).append("] ");
            }
        }
        return combiningObjectTitles;
    }

    @Override
    public int hashCode() {
        int result = title == null ? 0 : title.hashCode();
        result = 31 * result + officeRoom;
        result = 31 * result + patients.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Department that = (Department) obj;
        if (!Objects.equals(title, that.title)) return false;
        if (!Objects.equals(patients, that.patients)) return false;
        return officeRoom == that.officeRoom;
    }

    @Override
    public String toString() {
        return "Department '" + title + "':" +
                "\n\tEmployees: " + combineObjectTitles(employees);
    }
}
