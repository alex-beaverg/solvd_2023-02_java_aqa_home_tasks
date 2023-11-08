package org.example.hospital.structure;

import org.example.hospital.people.Employee;
import org.example.hospital.people.Patient;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Department implements IAddPatients, IGetEmployeesBySomething {
    private final String title;
    private final int officeNumber;
    private Employee[] employees;
    private final ArrayList<Patient> patients;

    {
        patients = new ArrayList<>();
    }

    public Department(String title,
                      int officeNumber) {
        this.title = title;
        this.officeNumber = officeNumber;
    }

    @Override
    public final void addPatient(Patient patient) {
        patients.add(patient);
    }

    public final String getTitle() {
        return title;
    }

    public final int getOfficeNumber() {
        return officeNumber;
    }

    public final void setEmployees(Employee[] employees) {
        this.employees = employees;
    }

    @Override
    public final ArrayList<Employee> getEmployeesBySpecialistClass(int specialistClass) {
        ArrayList<Employee> tempList = new ArrayList<>();
        for (Employee employee: employees) {
            if (employee.getPosition().getSpecialistClass() == specialistClass) {
                tempList.add(employee);
            }
        }
        return tempList;
    }

    public final Employee getRandomEmployeeBySpecialistClass(int specialistClass) {
        ArrayList<Employee> tempList = new ArrayList<>();
        for (Employee employee: employees) {
            if (employee.getPosition().getSpecialistClass() == specialistClass) {
                tempList.add(employee);
            }
        }
        Random random = new Random();
        return tempList.get(random.nextInt(tempList.size()));
    }

    private StringBuilder combineObjectTitles(Object[] objects) {
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
        result = 31 * result + officeNumber;
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
        return officeNumber == that.officeNumber;
    }

    @Override
    public String toString() {
        return "Department '" + title + "' / Employees: " + combineObjectTitles(employees);
    }
}
