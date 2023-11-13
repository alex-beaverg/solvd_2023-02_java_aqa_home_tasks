package org.example.hospital.structure;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.hospital.people.Diagnosis;
import org.example.hospital.people.Employee;
import org.example.hospital.people.Patient;

import java.util.*;

public final class Hospital implements IAddPatients, IGetEmployeesBySomething {
    private final String title;
    private final List<Department> departments;
    private final Set<Employee> employees;
    private final List<Patient> patients;
    private final Map<Diagnosis, List<Patient>> diagnosesMap;
    private static final Logger LOGGER;
    private static final Logger LOGGER_LN;
    private static final Logger LN_LOGGER_LN;

    static {
        LOGGER = LogManager.getLogger("InsteadOfSOUT");
        LOGGER_LN = LogManager.getLogger("InsteadOfSOUT_ln");
        LN_LOGGER_LN = LogManager.getLogger("ln_InsteadOfSOUT_ln");
    }

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
        List<Patient> tempList = diagnosesMap.get(patient.getDiagnosis());
        if (tempList == null) {
            tempList = new ArrayList<>();
        }
        tempList.add(patient);
        diagnosesMap.put(patient.getDiagnosis(), tempList);
    }

    public void showDepartments() {
        LN_LOGGER_LN.info("All departments in hospital:");
        for (Department department : getDepartments()) {
            LOGGER_LN.info("- " + department);
        }
    }

    public void showEmployees() {
        LN_LOGGER_LN.info("All employees in hospital:");
        for (Employee employee : getEmployees()) {
            LOGGER_LN.info("- " + employee.getPersonToPrintInList());
        }
    }

    public void showDiagnosesMap() {
        LN_LOGGER_LN.info("Hospital diagnoses map:");
        for (Map.Entry<Diagnosis, List<Patient>> entry : diagnosesMap.entrySet()) {
            LOGGER.info("- " + entry.getKey() + ": ");
            for (Patient patient : entry.getValue()) {
                LOGGER.info("[" + patient.getFullName() + "] ");
            }
            LOGGER_LN.info("");
        }
    }

    public void showDoctorsWithTheirPatients() {
        LN_LOGGER_LN.info("All doctors with their patients:");
        for (Employee doctor : getEmployeesBySpecialistClass(2)) {
            LOGGER.info("- " + doctor.getFullName() + ": ");
            for (Patient patient : doctor.getPatients()) {
                LOGGER.info("[" + patient.getFullName() + "] ");
            }
            LOGGER_LN.info("");
        }
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

    private StringBuilder combineObjectTitles(List<Department> departs) {
        StringBuilder combiningObjectTitles = new StringBuilder();
        for (Department department: departs) {
            combiningObjectTitles.append("[").append(department.getTitle()).append("] ");
        }
        return combiningObjectTitles;
    }

    @Override
    public String toString() {
        return "Hospital '" + title + "' / Departments: " + combineObjectTitles(departments);
    }
}
