package com.solvd.hospital_project.hospital.structure;

import com.solvd.hospital_project.hospital.people.Employee;
import com.solvd.hospital_project.hospital.people.Patient;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Department implements IAddPatients, IGetEmployeesBySomething {
    private final String title;
    private final int officeNumber;
    private final String diseasesType;
    private final List<Employee> employees;
    private final List<Patient> patients;

    {
        employees = new ArrayList<>();
        patients = new ArrayList<>();
    }

    public Department(String title,
                      int officeNumber,
                      String diseasesType) {
        this.title = title;
        this.officeNumber = officeNumber;
        this.diseasesType = diseasesType;
    }

    @Override
    public final void addPatient(Patient patient) {
        if (!patients.contains(patient)) {
            patients.add(patient);
        }
    }

    public final String getTitle() {
        return title;
    }

    public String getDiseasesType() {
        return diseasesType;
    }

    public final int getOfficeNumber() {
        return officeNumber;
    }

    public final void addEmployee(Employee employee) {
        employees.add(employee);
    }

    @Override
    public final List<Employee> getEmployeesBySpecialistClass(int specialistClass) {
        List<Employee> tempList = new ArrayList<>();
        employees.stream()
                .filter(employee -> employee.getPosition().getSpecialistClass() == specialistClass)
                .forEach(tempList::add);
//        for (Employee employee: employees) {
//            if (employee.getPosition().getSpecialistClass() == specialistClass) {
//                tempList.add(employee);
//            }
//        }
        return tempList;
    }

    public final Employee getRandomEmployeeBySpecialistClass(int specialistClass) {
        ArrayList<Employee> tempList = new ArrayList<>();
        employees.stream()
                .filter(employee -> employee.getPosition().getSpecialistClass() == specialistClass)
                .forEach(tempList::add);
//        for (Employee employee: employees) {
//            if (employee.getPosition().getSpecialistClass() == specialistClass) {
//                tempList.add(employee);
//            }
//        }
        Random random = new Random();
        return tempList.get(random.nextInt(tempList.size()));
    }

    private String combineEmployees() {
        StringBuilder combiningEmployees = new StringBuilder();
        for (Employee employee: employees) {
            combiningEmployees.append("[").append(employee.getFullName()).append("] ");
        }
        return combiningEmployees.toString().trim();
    }

    private String combinePatients() {
        StringBuilder combiningPatients = new StringBuilder();
        for (Patient patient: patients) {
            combiningPatients.append("[").append(patient.getFullName()).append("] ");
        }
        return combiningPatients.toString().trim();
    }

    @Override
    public int hashCode() {
        int result = title == null ? 0 : title.hashCode();
        result = 31 * result + officeNumber;
        result = 31 * result + (diseasesType == null ? 0 : diseasesType.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Department that = (Department) obj;
        if (!Objects.equals(title, that.title)) return false;
        if (!Objects.equals(diseasesType, that.diseasesType)) return false;
        return officeNumber == that.officeNumber;
    }

    @Override
    public String toString() {
        return "Department '" + title + "':" +
                "\n  - Employees: " + combineEmployees() +
                "\n  - Patients: " + combinePatients();
    }
}
