package com.solvd.hospital_project.hospital.structure;

import com.solvd.hospital_project.hospital.people.*;

import java.util.*;

public final class Hospital implements IAddPatients, IGetEmployeesBySomething {
    private final String title;
    private final List<Department> departments;
    private final Set<Employee> employees;
    private final List<Patient> patients;
    private final Map<Patient, List<Diagnosis>> diagnosesMap;

    {
        departments = new ArrayList<>();
        employees = new HashSet<>();
        patients = new ArrayList<>();
        diagnosesMap = new HashMap<>();
    }

    public Hospital(String title) {
        this.title = title;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public Map<Patient, List<Diagnosis>> getDiagnosesMap() {
        return diagnosesMap;
    }

    @Override
    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public void addDepartment(Department department) {
        departments.add(department);
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void addPatientToDiagnosesMap(Patient patient) {
        diagnosesMap.put(patient, patient.getDiagnoses());
    }

    @Override
    public List<Employee> getEmployeesBySpecialistClass(int specialistClass) {
        List<Employee> tempList = new ArrayList<>();
        for (Employee employee: employees) {
            if (employee.getPosition().getSpecialistClass() == specialistClass) {
                tempList.add(employee);
            }
        }
        return tempList;
    }

    private String combineDepartments() {
        StringBuilder combiningDepartments = new StringBuilder();
        for (Department department: departments) {
            combiningDepartments.append("[").append(department.getTitle()).append("] ");
        }
        return combiningDepartments.toString().trim();
    }

    @Override
    public String toString() {
        return "Hospital '" + title + "' / Departments: " + combineDepartments();
    }
}
