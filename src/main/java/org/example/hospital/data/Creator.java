package org.example.hospital.data;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.hospital.people.*;
import org.example.hospital.structure.Department;
import org.example.hospital.structure.Hospital;
import org.example.hospital.structure.Service;
import org.example.hospital.structure.VipService;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Creator {
    private static final Logger LOGGER_TO_CONSOLE_AND_FILE;

    static {
        LOGGER_TO_CONSOLE_AND_FILE = LogManager.getLogger(Creator.class);
    }

    public static Hospital setHospital(String title) {
        return new Hospital(title);
    }

    public static Department setDepartment(String title, int officeNumber, String diseaseType) {
        return new Department(title, officeNumber, diseaseType);
    }

    public static Employee setEmployee(String firstName, String lastName, int age, String city, String street,
                                       int house, int flat, Department department, Position position,
                                       Schedule schedule) {
        Address address = setAddress(city, street, house, flat);
        return new Employee(firstName, lastName, age, address, department, position, schedule);
    }

    public static Employee setEmployee(String firstName, String lastName, int age, Address address,
                                       Department department, Position position, Schedule schedule) {
        return new Employee(firstName, lastName, age, address, department, position, schedule);
    }

    public static Patient setPatient() {
        return new Patient();
    }

    public static Patient setPatient(String firstName, String lastName, int age, String city, String street,
                                     int house, int flat, Diagnosis diagnosis, Department department) {
        Address address = setAddress(city, street, house, flat);
        return new Patient(firstName, lastName, age, address, diagnosis, department);
    }

    public static Address setAddress() {
        return new Address();
    }

    public static Address setAddress(String city, String street, int house, int flat) {
        return new Address(city, street, house, flat);
    }

    public static Address setAddress(String path) {
        try (FileReader fileReader = new FileReader(path)) {
            Scanner scanner = new Scanner(fileReader);
            String city = scanner.nextLine();
            String street = scanner.nextLine();
            int house = Integer.parseInt(scanner.nextLine());
            int flat = Integer.parseInt(scanner.nextLine());
            return new Address(city, street, house, flat);
        } catch (IOException e) {
            LOGGER_TO_CONSOLE_AND_FILE.error("[IOException]: File not found!");
            return null;
        } catch (NumberFormatException e) {
            LOGGER_TO_CONSOLE_AND_FILE.error("[NumberFormatException]: Entered data is not a number!");
            return null;
        }
    }

    public static Hospital generateHospital() {
        // Create object Hospital:
        Hospital hospital = setHospital("City Hospital");
        // Add departments to Hospital:
        for (Department department : generateDepartments()) {
            hospital.addDepartment(department);
        }
        // Add employees to Hospital:
        for (Employee employee : generateEmployees(hospital)) {
            hospital.addEmployee(employee);
        }
        // Add employees to departments:
        for (Department department : hospital.getDepartments()) {
            for (Employee employee : hospital.getEmployees()) {
                if (employee.getDepartment().equals(department)) {
                    department.addEmployee(employee);
                }
            }
        }
        // Add patients to Hospital:
        for (Patient patient : generatePatients(hospital)) {
            hospital.addPatient(patient);
            hospital.addPatientToDiagnosesMap(patient);
        }
        // Add patients to departments:
        for (Department department : hospital.getDepartments()) {
            for (Patient patient : hospital.getPatients()) {
                if (patient.getDepartment().equals(department)) {
                    department.addPatient(patient);
                }
            }
        }
        // Add doctors and nurses to patients:
        // Also add patients to their doctors:
        for (Patient patient : hospital.getPatients()) {
            for (Department department : hospital.getDepartments()) {
                if (department.equals(patient.getDepartment())) {
                    patient.setDoctor(department.getRandomEmployeeBySpecialistClass(2));
                    patient.setNurse(department.getRandomEmployeeBySpecialistClass(1));
                    patient.getDoctor().addPatient(patient);
                }
            }
        }
        // Add random services and VIP services to patients and to their doctors:
        Random random = new Random();
        for (Patient patient : hospital.getPatients()) {
            do {
                Service service = Service.values()[random.nextInt(Service.values().length)];
                if (!patient.getServices().contains(service)) {
                    patient.addService(service);
                    patient.getDoctor().addService(service);
                }
            } while (patient.getServices().size() < 2);
            do {
                VipService vipService = VipService.values()[random.nextInt(VipService.values().length)];
                if (!patient.getVipServices().contains(vipService)) {
                    patient.addVipService(vipService);
                    patient.getDoctor().addVipService(vipService);
                }
            } while (patient.getVipServices().size() < 2);
        }
        return hospital;
    }

    private static List<Department> generateDepartments() {
        List<Department> departments = new ArrayList<>();
        departments.add(setDepartment("Therapeutic department", 103, "General"));
        departments.add(setDepartment("Surgery department", 202, "Fractures"));
        return departments;
    }

    private static List<Employee> generateEmployees(Hospital hospital) {
        List<Employee> employees = new ArrayList<>();
        employees.add(setEmployee("John", "Smith", 55, "Minsk", "Old avenue",
                5, 100, hospital.getDepartments().get(0), Position.DEPT_HEAD, Schedule.BEFORE_NOON_EVEN_DAYS));
        employees.add(setEmployee("Alex", "White", 51, "Minsk", "New street",
                10, 215, hospital.getDepartments().get(1), Position.DEPT_HEAD, Schedule.BEFORE_NOON_ODD_DAYS));
        employees.add(setEmployee("Emma", "Greet", 29, "Minsk", "Yellow avenue",
                1, 1, hospital.getDepartments().get(0), Position.DOCTOR_1_CATEGORY, Schedule.AFTERNOON_EVEN_DAYS));
        employees.add(setEmployee("Jake", "Tube", 35, "Minsk", "Red street",
                4, 100, hospital.getDepartments().get(0), Position.DOCTOR_2_CATEGORY, Schedule.AFTERNOON_ODD_DAYS));
        employees.add(setEmployee("Sam", "Freeze", 35, "Minsk", "Gold street",
                43, 60, hospital.getDepartments().get(0), Position.DOCTOR_3_CATEGORY, Schedule.BEFORE_NOON_ODD_DAYS));
        employees.add(setEmployee("Bob", "Torn", 31, "Minsk", "Green street",
                31, 75, hospital.getDepartments().get(1), Position.DOCTOR_1_CATEGORY, Schedule.AFTERNOON_ODD_DAYS));
        employees.add(setEmployee("Les", "Drop", 38, "Minsk", "Old avenue",
                61, 23, hospital.getDepartments().get(1), Position.DOCTOR_2_CATEGORY, Schedule.AFTERNOON_EVEN_DAYS));
        employees.add(setEmployee("Chris", "Stone", 33, "Minsk", "Old avenue",
                12, 35, hospital.getDepartments().get(1), Position.DOCTOR_3_CATEGORY, Schedule.BEFORE_NOON_ODD_DAYS));
        employees.add(setEmployee("Helga", "Moon", 25, "Minsk", "Green street",
                32, 56, hospital.getDepartments().get(0), Position.NURSE, Schedule.BEFORE_NOON_ODD_DAYS));
        employees.add(setEmployee("Steeve", "Hawk", 24, "Minsk", "Red street",
                2, 7, hospital.getDepartments().get(1), Position.NURSE, Schedule.BEFORE_NOON_EVEN_DAYS));
        return employees;
    }

    private static List<Patient> generatePatients(Hospital hospital) {
        List<Patient> patients = new ArrayList<>();
        patients.add(setPatient("Eric", "Adams", 30, "Minsk", "Main street",
                22, 11, Diagnosis.FLU, hospital.getDepartments().get(0)));
        patients.add(setPatient("Lisa", "Bourne", 28, "Minsk", "Green street",
                45, 9, Diagnosis.BONE_FRACTURE, hospital.getDepartments().get(1)));
        patients.add(setPatient("Rose", "Dart", 23, "Brest", "Old avenue",
                4, 66, Diagnosis.COVID, hospital.getDepartments().get(0)));
        patients.add(setPatient("Max", "Corn", 33, "Minsk", "Red street",
                5, 97, Diagnosis.UNKNOWN, hospital.getDepartments().get(0)));
        return patients;
    }
}
