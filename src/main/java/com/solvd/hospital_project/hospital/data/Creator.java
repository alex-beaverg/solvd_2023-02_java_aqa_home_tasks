package com.solvd.hospital_project.hospital.data;

import com.solvd.hospital_project.hospital.structure.Department;
import com.solvd.hospital_project.hospital.structure.Hospital;
import com.solvd.hospital_project.hospital.structure.Service;
import com.solvd.hospital_project.hospital.structure.VipService;
import com.solvd.hospital_project.hospital.people.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Creator {
    private static String diagnosisType;
    public static volatile RegistrationPool registrationPool = RegistrationPool.getInstance(2);

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

    public static Patient setPatient() {
        return new Patient();
    }

    public synchronized static Patient setPatient(String firstName, String lastName, int age, String city, String street,
                                     int house, int flat, List<Diagnosis> diagnoses, Department department) {
        Address address = setAddress(city, street, house, flat);
        return new Patient(firstName, lastName, age, address, diagnoses, department);
    }

    public static Address setAddress() {
        return new Address();
    }

    public static Address setAddress(String city, String street, int house, int flat) {
        return new Address(city, street, house, flat);
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
        departments.add(setDepartment("Surgery department", 202, "Injuries"));
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

    private static List<Diagnosis> generateRandomDiagnosesList() {
        List<Diagnosis> diagnoses = new ArrayList<>();
        Random random = new Random();
        do {
            Diagnosis diagnosis = Diagnosis.values()[random.nextInt(Diagnosis.values().length)];
            if (diagnoses.isEmpty()) {
                diagnoses.add(diagnosis);
                diagnosisType = diagnosis.getType();
                continue;
            }
            if (!diagnoses.contains(diagnosis) && diagnoses.get(0).getType().equals(diagnosis.getType())) {
                diagnoses.add(diagnosis);
            }
        } while (diagnoses.size() < 2);
        return diagnoses;
    }

    private static List<Patient> generatePatients(Hospital hospital) {
        List<Patient> patients = new ArrayList<>();
        Registration registration1 = registrationPool.getRegistration();
        Thread threadRegistration1 = new Thread(() -> {
            registration1.run();
            patients.add(setPatient("Eric", "Adams", 30, "Minsk", "Main street",
                    22, 11, generateRandomDiagnosesList(),
                    hospital.getDepartments().get(diagnosisType.equals("General") ? 0 : 1)));
        });
        threadRegistration1.start();

        Registration registration2 = registrationPool.getRegistration();
        Thread threadRegistration2 = new Thread(() -> {
            registration2.run();
            patients.add(setPatient("Lisa", "Bourne", 28, "Minsk", "Green street",
                    45, 9, generateRandomDiagnosesList(),
                    hospital.getDepartments().get(diagnosisType.equals("General") ? 0 : 1)));
        });
        threadRegistration2.start();

        Registration registration3 = registrationPool.getRegistration();
        Thread threadRegistration3 = new Thread(() -> {
            registration3.run();
            patients.add(setPatient("Rose", "Dart", 23, "Brest", "Old avenue",
                    4, 66, generateRandomDiagnosesList(),
                    hospital.getDepartments().get(diagnosisType.equals("General") ? 0 : 1)));
        });
        threadRegistration3.start();

        Registration registration4 = registrationPool.getRegistration();
        Thread threadRegistration4 = new Thread(() -> {
            registration4.run();
            patients.add(setPatient("Max", "Corn", 33, "Minsk", "Red street",
                    5, 97, generateRandomDiagnosesList(),
                    hospital.getDepartments().get(diagnosisType.equals("General") ? 0 : 1)));
        });
        threadRegistration4.start();

        while (true) {
            if (registrationPool.getNumberOfAvailableRegistrations() == registrationPool.getPoolSize()) {
                break;
            }
        } // it is used to complete previous block of code

        return patients;
    }
}
