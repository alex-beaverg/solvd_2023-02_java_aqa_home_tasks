package com.solvd.hospital_project.hospital.structure;

import static com.solvd.hospital_project.hospital.util.Printers.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.hospital_project.hospital.util.RequestMethods;
import com.solvd.hospital_project.hospital.custom_exceptions.*;
import com.solvd.hospital_project.hospital.people.*;
import com.solvd.hospital_project.hospital.data.Creator;

import java.util.List;
import java.util.Map;

public class GeneralActions {
    private static final Logger LOGGER;

    static {
        LOGGER = LogManager.getLogger(GeneralActions.class);
    }

    public static Person choosePersonFromList(String personType, Hospital hospital) {
        int index = 1;
        PRINT2LN.info("All " + personType + "s in the hospital:");
        if (personType.equals("doctor")) {
            for (Employee doctor: hospital.getEmployeesBySpecialistClass(2)) {
                PRINTLN.info("[" + index + "] - " + doctor.getPersonToPrintInList());
                index++;
            }
        } else {
            for (Patient patient: hospital.getPatients()) {
                PRINTLN.info("[" + index + "] - " + patient.getPersonToPrintInList());
                index++;
            }
        }
        int answer;
        do {
            try {
                answer = RequestMethods.requestingInfoWithChoice("Enter number of " + personType + " to choose him: ", index - 1);
                break;
            } catch (EmptyInputException | MenuItemOutOfBoundsException e) {
                LOGGER.error(e.getMessage());
            } catch (NumberFormatException e) {
                LOGGER.error("[NumberFormatException]: Entered data is not a number!");
            }
        } while (true);
        Person person;
        if (personType.equals("doctor")) {
            person = hospital.getEmployeesBySpecialistClass(2).get(answer - 1);
        } else {
            person = hospital.getPatients().get(answer - 1);
        }
        PRINTLN.info("Doctor " + person.getFullName() + " was chosen");
        return person;
    }

    public static Patient findExistPatient(Hospital hospital) {
        String fullName;
        do {
            try {
                fullName = RequestMethods.requestingInfoString("\nEnter patient full name for searching: ");
                break;
            } catch (EmptyInputException | StringFormatException e) {
                LOGGER.error(e.getMessage());
            }
        } while (true);
        for (Patient existPatient: hospital.getPatients()) {
            if ((existPatient.getFullName()).equalsIgnoreCase(fullName)) {
                PRINTLN.info("Patient " + existPatient.getFullName() + " was found and chosen");
                return existPatient;
            }
        }
        PRINTLN.info("Patient " + fullName + " was not found. Try it again");
        return null;
    }

    public static Patient registerNewPatient(Hospital hospital){
        Patient newPatient = Creator.setPatient();
        Address address = Creator.setAddress();
        PRINT2LN.info("Registration of a new patient");
        do {
            try {
                newPatient.setFirstName(RequestMethods.requestingInfoString("Enter your first name: "));
                break;
            } catch (EmptyInputException | StringFormatException e) {
                LOGGER.error(e.getMessage());
            }
        } while (true);
        do {
            try {
                newPatient.setLastName(RequestMethods.requestingInfoString("Enter your last name: "));
                break;
            } catch (EmptyInputException | StringFormatException e) {
                LOGGER.error(e.getMessage());
            }
        } while (true);
        do {
            try {
                newPatient.setAge(getAgeFromConsole());
                break;
            } catch (AgeException e) {
                LOGGER.error(e.getMessage());
            }
        } while (true);
        do {
            try {
                address.setCity(RequestMethods.requestingInfoString("Enter your city: "));
                break;
            } catch (EmptyInputException | StringFormatException e) {
                LOGGER.error(e.getMessage());
            }
        } while (true);
        do {
            try {
                address.setStreet(RequestMethods.requestingInfoString("Enter your street: "));
                break;
            } catch (EmptyInputException | StringFormatException e) {
                LOGGER.error(e.getMessage());
            }
        } while (true);
        do {
            try {
                address.setHouseNumber(RequestMethods.requestingInfoInt("Enter your house number: "));
                break;
            } catch (EmptyInputException | NegativeNumberException e) {
                LOGGER.error(e.getMessage());
            } catch (NumberFormatException e) {
                LOGGER.error("[NumberFormatException]: Entered data is not a number!");
            }
        } while (true);
        do {
            try {
                address.setFlatNumber(RequestMethods.requestingInfoInt("Enter your flat number: "));
                break;
            } catch (EmptyInputException | NegativeNumberException e) {
                LOGGER.error(e.getMessage());
            } catch (NumberFormatException e) {
                LOGGER.error("[NumberFormatException]: Entered data is not a number!");
            }
        } while (true);
        newPatient.setAddress(address);
        hospital.addPatient(newPatient);
        PRINTLN.info("New patient (" + newPatient.getFullName() + ") was registered");
        return newPatient;
    }

    private static int getAgeFromConsole() throws AgeException {
        do {
            try {
                int age = RequestMethods.requestingInfoInt("Enter your age: ");
                if (age > 122) {
                    throw new AgeException("[AgeException]: Age can not be more than 122 years");
                }
                return age;
            } catch (EmptyInputException | NegativeNumberException e) {
                LOGGER.error(e.getMessage());
            } catch (NumberFormatException e) {
                LOGGER.error("[NumberFormatException]: Entered data is not a number!");
            }
        } while (true);
    }

    public static Diagnosis getDiagnose(Hospital hospital, Patient patient, int number) {
        switch (number) {
            case (1) -> {
                Diagnosis diagnosis = Diagnosis.FLU;
                for (Department department : hospital.getDepartments()) {
                    if (department.getDiseasesType().equals(diagnosis.getType())) {
                        patient.setDepartment(department);
                        patient.setDoctor(department.getRandomEmployeeBySpecialistClass(2));
                        patient.setNurse(department.getRandomEmployeeBySpecialistClass(1));
                        department.addPatient(patient);
                    }
                }
                return diagnosis;
            }
            case (2) -> {
                Diagnosis diagnosis = Diagnosis.COVID;
                for (Department department : hospital.getDepartments()) {
                    if (department.getDiseasesType().equals(diagnosis.getType())) {
                        patient.setDepartment(department);
                        patient.setDoctor(department.getRandomEmployeeBySpecialistClass(2));
                        patient.setNurse(department.getRandomEmployeeBySpecialistClass(1));
                        department.addPatient(patient);
                    }
                }
                return diagnosis;
            }
            case (3) -> {
                Diagnosis diagnosis = Diagnosis.BONE_FRACTURE;
                for (Department department : hospital.getDepartments()) {
                    if (department.getDiseasesType().equals(diagnosis.getType())) {
                        patient.setDepartment(department);
                        patient.setDoctor(department.getRandomEmployeeBySpecialistClass(2));
                        patient.setNurse(department.getRandomEmployeeBySpecialistClass(1));
                        department.addPatient(patient);
                    }
                }
                return diagnosis;
            }
            case (4) -> {
                Diagnosis diagnosis = Diagnosis.HAND_INJURY;
                for (Department department : hospital.getDepartments()) {
                    if (department.getDiseasesType().equals(diagnosis.getType())) {
                        patient.setDepartment(department);
                        patient.setDoctor(department.getRandomEmployeeBySpecialistClass(2));
                        patient.setNurse(department.getRandomEmployeeBySpecialistClass(1));
                        department.addPatient(patient);
                    }
                }
                return diagnosis;
            }
            case (5) -> {
                Diagnosis diagnosis = Diagnosis.LEG_INJURY;
                for (Department department : hospital.getDepartments()) {
                    if (department.getDiseasesType().equals(diagnosis.getType())) {
                        patient.setDepartment(department);
                        patient.setDoctor(department.getRandomEmployeeBySpecialistClass(2));
                        patient.setNurse(department.getRandomEmployeeBySpecialistClass(1));
                        department.addPatient(patient);
                    }
                }
                return diagnosis;
            }
            default -> {
                Diagnosis diagnosis = Diagnosis.UNKNOWN;
                for (Department department : hospital.getDepartments()) {
                    if (department.getDiseasesType().equals(diagnosis.getType())) {
                        patient.setDepartment(department);
                        patient.setDoctor(department.getRandomEmployeeBySpecialistClass(2));
                        patient.setNurse(department.getRandomEmployeeBySpecialistClass(1));
                        department.addPatient(patient);
                    }
                }
                return diagnosis;
            }
        }
    }

    public static void showDepartments(Hospital hospital) {
        PRINT2LN.info("All departments in hospital:");
        for (Department department : hospital.getDepartments()) {
            PRINTLN.info("- " + department);
        }
    }

    public static void showEmployees(Hospital hospital) {
        PRINT2LN.info("All employees in hospital:");
        for (Employee employee : hospital.getEmployees()) {
            PRINTLN.info("- " + employee.getPersonToPrintInList());
        }
    }

    public static void showDiagnosesMap(Hospital hospital) {
        PRINT2LN.info("Hospital diagnoses map:");
        for (Map.Entry<Patient, List<Diagnosis>> entry : hospital.getDiagnosesMap().entrySet()) {
            PRINT.info("- " + entry.getKey().getFullName() + ": ");
            for (Diagnosis diagnosis : entry.getValue()) {
                PRINT.info("[" + diagnosis.getTitle() + "] ");
            }
            PRINTLN.info("");
        }
    }

    public static void showDoctorsWithTheirPatients(Hospital hospital) {
        PRINT2LN.info("All doctors with their patients:");
        for (Employee doctor : hospital.getEmployeesBySpecialistClass(2)) {
            PRINT.info("- " + doctor.getFullName() + ": ");
            for (Patient patient : doctor.getPatients()) {
                PRINT.info("[" + patient.getFullName() + "] ");
            }
            PRINTLN.info("");
        }
    }
}
