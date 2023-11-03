package org.example.hospital.structure;

import org.example.hospital.people.Employee;
import org.example.hospital.people.Patient;
import org.example.hospital.people.Position;

import java.util.ArrayList;
import java.util.Objects;

public class Department {
    private final String departmentTitle;
    private final int officeRoom;
    private Employee[] employees;
    private final ArrayList<Patient> patients = new ArrayList<>();

    public Department(String departmentTitle,
                      int officeRoom) {
        this.departmentTitle = departmentTitle;
        this.officeRoom = officeRoom;
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public String getDepartmentTitle() {
        return departmentTitle;
    }

    public int getOfficeRoom() {
        return officeRoom;
    }

    public void setEmployees(Employee[] employees) {
        this.employees = employees;
    }

    public Employee getEmployee(Position position) {
        for (Employee employee: employees) {
            if (employee.getPosition() == position) {
                return employee;
            }
        }
        return null;
    }

    @Override
    public int hashCode() {
        int result = departmentTitle == null ? 0 : departmentTitle.hashCode();
        result = 31 * result + officeRoom;
        result = 31 * result + patients.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Department that = (Department) obj;
        if (!Objects.equals(departmentTitle, that.departmentTitle)) return false;
        if (!Objects.equals(patients, that.patients)) return false;
        return officeRoom == that.officeRoom;
    }
}
