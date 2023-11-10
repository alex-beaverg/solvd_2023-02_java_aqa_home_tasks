package org.example.hospital.structure;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.hospital.custom_exceptions.*;
import org.example.hospital.data.Creator;
import org.example.hospital.people.Address;
import org.example.hospital.people.Diagnosis;
import org.example.hospital.people.Employee;
import org.example.hospital.people.Patient;
import org.example.hospital.util.RequestMethods;

import java.util.ArrayList;
import java.util.List;

public final class Hospital implements IAddPatients, IGetEmployeesBySomething {
    private final static Logger LOGGER_LN;
    private final static Logger LN_LOGGER_LN;
    private final static Logger LOGGER_TO_CONSOLE_AND_FILE;
    private final String title;
    private final List<Department> departments;
    private final List<Employee> employees;
    private final List<Patient> patients;

    static {
        LOGGER_LN = LogManager.getLogger("InsteadOfSOUT_ln");
        LN_LOGGER_LN = LogManager.getLogger("ln_InsteadOfSOUT_ln");
        LOGGER_TO_CONSOLE_AND_FILE = LogManager.getLogger("Errors_To_Console_And_File");
    }

    {
        departments = new ArrayList<>();
        employees = new ArrayList<>();
        patients = new ArrayList<>();
    }

    public Hospital(String title) {
        this.title = title;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public List<Employee> getEmployees() {
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

    public Employee showDoctors() {
        int index = 1;
        LN_LOGGER_LN.info("All doctors in the hospital:");
        for (Employee doctor: getEmployeesBySpecialistClass(2)) {
            LOGGER_LN.info("[" + index + "] - " + doctor.getPersonToPrintInList());
            index++;
        }
        int answer;
        do {
            try {
                answer = RequestMethods.requestingInfoWithChoice("Enter number of doctor to choose him: ", index - 1);
                break;
            } catch (EmptyInputException | MenuItemNumberOutOfBoundsException e) {
                LOGGER_TO_CONSOLE_AND_FILE.error(e.getMessage());
            } catch (NumberFormatException e) {
                LOGGER_TO_CONSOLE_AND_FILE.error("[NumberFormatException]: Entered data is not a number!");
            }
        } while (true);
        Employee doctor = getEmployeesBySpecialistClass(2).get(answer - 1);
        LOGGER_LN.info("Doctor " + doctor.getFullName() + " was chosen");
        return doctor;
    }

    public Patient findExistPatient() {
        String fullName;
        do {
            try {
                fullName = RequestMethods.requestingInfoString("\nEnter patient full name for searching: ");
                break;
            } catch (EmptyInputException | StringFormatException e) {
                LOGGER_TO_CONSOLE_AND_FILE.error(e.getMessage());
            }
        } while (true);
        for (Patient existPatient: getPatients()) {
            if ((existPatient.getFullName()).equalsIgnoreCase(fullName)) {
                LOGGER_LN.info("Patient " + existPatient.getFullName() + " was found and chosen");
                return existPatient;
            }
        }
        LOGGER_LN.info("Patient " + fullName + " was not found. Try it again");
        return null;
    }

    public Patient registerNewPatient(){
        Patient newPatient = Creator.setPatient();
        Address address = Creator.setAddress();
        LN_LOGGER_LN.info("Registration of a new patient");
        do {
            try {
                newPatient.setFirstName(RequestMethods.requestingInfoString("Enter your first name: "));
                break;
            } catch (EmptyInputException | StringFormatException e) {
                LOGGER_TO_CONSOLE_AND_FILE.error(e.getMessage());
            }
        } while (true);
        do {
            try {
                newPatient.setLastName(RequestMethods.requestingInfoString("Enter your last name: "));
                break;
            } catch (EmptyInputException | StringFormatException e) {
                LOGGER_TO_CONSOLE_AND_FILE.error(e.getMessage());
            }
        } while (true);
        do {
            try {
                newPatient.setAge(getAgeFromConsole());
                break;
            } catch (AgeException e) {
                LOGGER_TO_CONSOLE_AND_FILE.error(e.getMessage());
            }
        } while (true);
        do {
            try {
                address.setCity(RequestMethods.requestingInfoString("Enter your city: "));
                break;
            } catch (EmptyInputException | StringFormatException e) {
                LOGGER_TO_CONSOLE_AND_FILE.error(e.getMessage());
            }
        } while (true);
        do {
            try {
                address.setStreet(RequestMethods.requestingInfoString("Enter your street: "));
                break;
            } catch (EmptyInputException | StringFormatException e) {
                LOGGER_TO_CONSOLE_AND_FILE.error(e.getMessage());
            }
        } while (true);
        do {
            try {
                address.setHouseNumber(RequestMethods.requestingInfoInt("Enter your house number: "));
                break;
            } catch (EmptyInputException | NegativeNumberException e) {
                LOGGER_TO_CONSOLE_AND_FILE.error(e.getMessage());
            } catch (NumberFormatException e) {
                LOGGER_TO_CONSOLE_AND_FILE.error("[NumberFormatException]: Entered data is not a number!");
            }
        } while (true);
        do {
            try {
                address.setFlatNumber(RequestMethods.requestingInfoInt("Enter your flat number: "));
                break;
            } catch (EmptyInputException | NegativeNumberException e) {
                LOGGER_TO_CONSOLE_AND_FILE.error(e.getMessage());
            } catch (NumberFormatException e) {
                LOGGER_TO_CONSOLE_AND_FILE.error("[NumberFormatException]: Entered data is not a number!");
            }
        } while (true);
        newPatient.setAddress(address);
        addPatient(newPatient);
        return newPatient;
    }

    private int getAgeFromConsole() throws AgeException {
        do {
            try {
                int age = RequestMethods.requestingInfoInt("Enter your age: ");
                if (age > 122) {
                    throw new AgeException("[AgeException]: Age can not be more than 122 years");
                }
                return age;
            } catch (EmptyInputException | NegativeNumberException e) {
                LOGGER_TO_CONSOLE_AND_FILE.error(e.getMessage());
            } catch (NumberFormatException e) {
                LOGGER_TO_CONSOLE_AND_FILE.error("[NumberFormatException]: Entered data is not a number!");
            }
        } while (true);
    }

    public Patient choosePatient() {
        int index = 1;
        LN_LOGGER_LN.info("All patients in the hospital:");
        for (Patient existPatient: getPatients()) {
            LOGGER_LN.info("[" + index + "] - " + existPatient.getPersonToPrintInList());
            index++;
        }
        int answer;
        do {
            try {
                answer = RequestMethods.requestingInfoWithChoice("Enter number of patient to choose him: ", index - 1);
                break;
            } catch (EmptyInputException | MenuItemNumberOutOfBoundsException e) {
                LOGGER_TO_CONSOLE_AND_FILE.error(e.getMessage());
            } catch (NumberFormatException e) {
                LOGGER_TO_CONSOLE_AND_FILE.error("[NumberFormatException]: Entered data is not a number!");
            }
        } while (true);
        return getPatients().get(answer - 1);
    }

    public Diagnosis getDiagnose(Patient patient, int number) {
        switch (number) {
            case (1) -> {
                Diagnosis diagnosis = Diagnosis.FLU;
                for (Department department : getDepartments()) {
                    if (department.getDiseasesType().equals(diagnosis.getType())) {
                        patient.setDepartment(department);
                        patient.setDoctor(department.getRandomEmployeeBySpecialistClass(2));
                        patient.setNurse(department.getRandomEmployeeBySpecialistClass(1));
                        department.addPatient(patient);
                        return diagnosis;
                    }
                }
            }
            case (2) -> {
                Diagnosis diagnosis = Diagnosis.COVID;
                for (Department department : getDepartments()) {
                    if (department.getDiseasesType().equals(diagnosis.getType())) {
                        patient.setDepartment(department);
                        patient.setDoctor(department.getRandomEmployeeBySpecialistClass(2));
                        patient.setNurse(department.getRandomEmployeeBySpecialistClass(1));
                        department.addPatient(patient);
                        return diagnosis;
                    }
                }
            }
            case (3) -> {
                Diagnosis diagnosis = Diagnosis.BONE_FRACTURE;
                for (Department department : getDepartments()) {
                    if (department.getDiseasesType().equals(diagnosis.getType())) {
                        patient.setDepartment(department);
                        patient.setDoctor(department.getRandomEmployeeBySpecialistClass(2));
                        patient.setNurse(department.getRandomEmployeeBySpecialistClass(1));
                        department.addPatient(patient);
                        return diagnosis;
                    }
                }
            }
            default -> {
                Diagnosis diagnosis = Diagnosis.UNKNOWN;
                for (Department department : getDepartments()) {
                    if (department.getDiseasesType().equals(diagnosis.getType())) {
                        patient.setDepartment(department);
                        patient.setDoctor(department.getRandomEmployeeBySpecialistClass(2));
                        patient.setNurse(department.getRandomEmployeeBySpecialistClass(1));
                        department.addPatient(patient);
                        return diagnosis;
                    }
                }
            }
        }
        return null;
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
