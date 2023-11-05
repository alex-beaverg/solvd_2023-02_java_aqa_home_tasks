package org.example.hospital.helpers;

import org.example.hospital.data.Creator;
import org.example.hospital.people.*;
import org.example.hospital.structure.Department;
import org.example.hospital.structure.Service;
import org.example.hospital.structure.VipService;

import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleMenu {
    private final Creator objects = new Creator();
    private Patient patient;
    Scanner scanner = new Scanner(System.in);

    public void runApp() {
        objects.fillArrays();
        System.out.println("\nWelcome to the " + objects.hospital);
        runMainMenu();
    }

    private void runMainMenu() {
        int answerInt = requestingInfoWithChoice(
                """
                        Choose an action:
                        [1] - Show departments
                        [2] - Doctors menu action
                        [3] - Patients menu action
                        [4] - Exit
                        """, 4);
        switch (answerInt) {
            case (1) -> {
                showDepartments();
                runMainMenu();
            }
            case (2) -> runDoctorsMenu();
            case (3) -> runPatientsMenu();
            default -> {
                scanner.close();
                System.out.println("Good bye!");
            }
        }
    }

    private void showDepartments() {
        for (Department department : objects.hospital.getDepartments()) {
            System.out.println(department);
        }
    }

    private void runDoctorsMenu() {
        int answerInt = requestingInfoWithChoice(
                """
                        Choose an action:
                        [1] - Show all doctors
                        [2] - Go to main menu
                        [3] - Exit
                        """, 3);
        if (answerInt == 1) {
            showDoctors();
            runDoctorsMenu();
        } else if (answerInt == 2) {
            runMainMenu();
        } else {
            scanner.close();
            System.out.println("Good bye!");
        }
    }

    private void runPatientsMenu() {
        int answerInt = requestingInfoWithChoice(
                """
                        Choose an action:
                        [1] - Find the patient in registry
                        [2] - Register new patient
                        [3] - Show all patients
                        [4] - Go to main menu
                        [5] - Exit
                        """, 5);
        switch (answerInt) {
            case (1) -> {
                patient = findExistPatient();
                if (patient != null) {
                    runPatientsSubmenu();
                }
            }
            case (2) -> {
                patient = registerNewPatient();
                System.out.println("New patient was registered");
                toComplain();
                System.out.println(patient);
                runPatientsSubmenu();
            }
            case (3) -> {
                patient = showPatients();
                runPatientsSubmenu();
            }
            case (4) -> runMainMenu();
            default -> {
                scanner.close();
                System.out.println("Good bye!");
            }
        }
    }

    private void runPatientsSubmenu() {
        int answerInt = requestingInfoWithChoice(
                """
                        Choose an action:
                        [1] - Go to main menu
                        [2] - Exit
                        """, 2);
        if (answerInt == 1) {
            runMainMenu();
        } else {
            scanner.close();
            System.out.println("Good bye!");
        }
    }

    private void showDoctors() {
        int index = 1;
        System.out.println("All doctors in the hospital:");
        ArrayList<Employee> tempList = new ArrayList<>();
        for (Employee doctor : objects.hospital.getEmployees()) {
            if (doctor.getPosition().getSpecialistClass() == 2) {
                System.out.println("[" + index + "] - " + doctor.getFirstName() + " " + doctor.getLastName() + " (" +
                        doctor.getPosition().getTitle() + ", " + doctor.getDepartment().getTitle() + ")");
                index++;
                tempList.add(doctor);
            }
        }
        int answerInt = requestingInfoWithChoice("Enter number of doctor to show more information: ", index - 1);
        System.out.println(tempList.get(answerInt - 1));
    }

    private Patient showPatients() {
        int index = 1;
        System.out.println("All patients in the hospital:");
        for (Patient existPatient : objects.hospital.getPatients()) {
            System.out.println("[" + index + "] - " + existPatient.getFirstName() + " " + existPatient.getLastName());
            index++;
        }
        int answerInt = requestingInfoWithChoice("Enter number of patient to show more information: ", index - 1);
        System.out.println(objects.hospital.getPatients().get(answerInt - 1));
        return objects.hospital.getPatients().get(answerInt - 1);
    }

    private void vipServiceMenu() {
        VipService vipService;
        do {
            vipService = getVipService(requestingInfoWithChoice(
                    """
                            Choose VIP service:
                            [1] - Provision of a separate room
                            [2] - Provision of a special food
                            [3] - Walk-in service
                            [4] - Providing entertainment
                            """, 4));
            patient.addVipService(vipService);
            String answer = requestingInfoWithYesOrNo("Do you want to choose another VIP service? (y/n): ");
            if (answer.equals("n")) {
                System.out.println("OK!");
                break;
            }
        } while (true);
    }

    private VipService getVipService(int number) {
        return switch (number) {
            case (1) -> VipService.SEPARATE_ROOM;
            case (2) -> VipService.SPECIAL_FOOD;
            case (3) -> VipService.WALK_IN_SERVICE;
            default -> VipService.ENTERTAINMENT;
        };
    }

    private void chooseTherapist() {
        int index = 1;
        System.out.println("All therapists in your department:");
        ArrayList<Employee> tempList = new ArrayList<>();
        for (Employee employee : patient.getDepartment().getEmployees()) {
            if (employee.getPosition().getSpecialistClass() == 2) {
                System.out.println("[" + index + "] - " + employee.getFirstName() + " " +
                        employee.getLastName() + " - " + employee.getPosition().getTitle());
                tempList.add(employee);
                index++;
            }
        }
        int answerInt = requestingInfoWithChoice("Enter number of therapist to choose him: ", index - 1);
        patient.setTherapist(tempList.get(answerInt - 1));
        System.out.println("Your therapist (" + patient.getTherapist().getFirstName() + " " + patient.getTherapist().getLastName() + ") was chosen!");
    }

    private Patient findExistPatient() {
        String fullName = requestingInfoString("Enter patient full name for searching: ");
        for (Patient existPatient : objects.hospital.getPatients()) {
            if ((existPatient.getFirstName() + " " + existPatient.getLastName()).equals(fullName)) {
                System.out.println("Patient " + fullName + " was found:");
                System.out.println(existPatient);
                return existPatient;
            }
        }
        System.out.println("Patient " + fullName + " was not found.");
        int answerInt = requestingInfoWithChoice(
                """
                        Choose an action:
                        [1] - Try to find the patient again
                        [2] - Register new patient
                        [3] - Show all patients to choose
                        [4] - Go to main menu
                        [5] - Exit
                        """, 5);
        switch (answerInt) {
            case (1) -> {
                return findExistPatient();
            }
            case (2) -> {
                patient = registerNewPatient();
                System.out.println("New patient was registered");
                toComplain();
                System.out.println(patient);
                return patient;
            }
            case (3) -> {
                return showPatients();
            }
            case (4) -> {
                runMainMenu();
                return null;
            }
            default -> {
                scanner.close();
                System.out.println("Good bye!");
                return null;
            }
        }
    }

    private Patient registerNewPatient() {
        Patient newPatient = new Patient();
        Address address = new Address();
        newPatient.setFirstName(requestingInfoString("Enter your first name: "));
        newPatient.setLastName(requestingInfoString("Enter your last name: "));
        newPatient.setAge(requestingInfoInt("Enter your age: "));
        address.setCity(requestingInfoString("Enter your city: "));
        address.setStreet(requestingInfoString("Enter your street: "));
        address.setHouseNumber(requestingInfoInt("Enter your house number: "));
        address.setFlatNumber(requestingInfoInt("Enter your flat number: "));
        newPatient.setAddress(address);
        objects.hospital.addPatient(newPatient);
        return newPatient;
    }

    private void toComplain() {
        if (patient != null) {
            patient.setDiagnosis(getDiagnose(requestingInfoWithChoice(
                    """
                            Enter your complaint:
                            [1] - Cough
                            [2] - No smells
                            [3] - Broken bone
                            [4] - Unknown
                            """, 4)));
        }
        Service service;
        do {
            service = getService(requestingInfoWithChoice(
                    """
                            Choose the service:
                            [1] - Make an appointment
                            [2] - Prescribe treatment
                            [3] - Hospitalization
                            [4] - Medical examination
                            """, 4));
            patient.addService(service);
            String answer = requestingInfoWithYesOrNo("Do you want to choose another service? (y/n): ");
            if (answer.equals("n")) {
                System.out.println("OK!");
                break;
            }
        } while (true);
        String answer = requestingInfoWithYesOrNo("Do you want to choose therapist? (y/n): ");
        if (answer.equals("y")) {
            chooseTherapist();
        } else {
            System.out.println("OK!");
        }
        addServicesToTherapist();
        answer = requestingInfoWithYesOrNo("Do you want to choose VIP service? (y/n): ");
        if (answer.equals("y")) {
            vipServiceMenu();
        } else {
            System.out.println("OK!");
        }
        addVipServicesToTherapist();
        patient.getTherapist().addPatient(patient);
    }

    private void addServicesToTherapist() {
        for (Service service : patient.getServices()) {
            patient.getTherapist().addService(service);
        }
    }

    private void addVipServicesToTherapist() {
        for (VipService vipService : patient.getVipServices()) {
            patient.getTherapist().addVipService(vipService);
        }
    }

    private Diagnosis getDiagnose(int number) {
        switch (number) {
            case (1) -> {
                patient.setDepartment(objects.therapeuticDept);
                patient.setTherapist(objects.therapeuticDept.getEmployeeBySpecialistClass(2));
                patient.setNurse(objects.therapeuticDept.getEmployeeBySpecialistClass(1));
                objects.therapeuticDept.addPatient(patient);
                return Diagnosis.FLU;
            }
            case (2) -> {
                patient.setDepartment(objects.therapeuticDept);
                patient.setTherapist(objects.therapeuticDept.getEmployeeBySpecialistClass(2));
                patient.setNurse(objects.therapeuticDept.getEmployeeBySpecialistClass(1));
                objects.therapeuticDept.addPatient(patient);
                return Diagnosis.COVID;
            }
            case (3) -> {
                patient.setDepartment(objects.surgeryDept);
                patient.setTherapist(objects.surgeryDept.getEmployeeBySpecialistClass(2));
                patient.setNurse(objects.surgeryDept.getEmployeeBySpecialistClass(1));
                objects.surgeryDept.addPatient(patient);
                return Diagnosis.BONE_FRACTURE;
            }
            default -> {
                patient.setDepartment(objects.therapeuticDept);
                patient.setTherapist(objects.therapeuticDept.getEmployeeBySpecialistClass(2));
                patient.setNurse(objects.therapeuticDept.getEmployeeBySpecialistClass(1));
                objects.therapeuticDept.addPatient(patient);
                return Diagnosis.UNKNOWN;
            }
        }
    }

    private Service getService(int number) {
        return switch (number) {
            case (1) -> Service.APPOINTMENT;
            case (2) -> Service.TREATMENT;
            case (3) -> Service.HOSPITALIZATION;
            default -> Service.EXAMINATION;
        };
    }

    private String requestingInfoWithYesOrNo(String text) {
        String answer;
        do {
            System.out.print(text);
            answer = scanner.nextLine();
            if (!answer.equals("y") && !answer.equals("n")) {
                text = "Please, enter correct data: ";
            } else {
                break;
            }
        } while (true);
        return answer;
    }

    private int requestingInfoWithChoice(String text, int to) {
        String answer;
        int numberFromAnswer = 0;
        do {
            System.out.print(text);
            answer = scanner.nextLine();
            try {
                numberFromAnswer = Integer.parseInt(answer);
            } catch (NumberFormatException e) {
                answer = "";
            }
            if (answer.isEmpty() || numberFromAnswer < 1 || numberFromAnswer > to) {
                text = "Please, enter correct data: ";
            } else {
                break;
            }
        } while (true);
        return numberFromAnswer;
    }

    private String requestingInfoString(String text) {
        String answer;
        do {
            System.out.print(text);
            answer = scanner.nextLine();
            if (answer.isEmpty()) {
                text = "Please, enter correct data: ";
            } else {
                break;
            }
        } while (true);
        return answer;
    }

    private int requestingInfoInt(String text) {
        String answer;
        int numberFromAnswer = 0;
        do {
            System.out.print(text);
            answer = scanner.nextLine();
            try {
                numberFromAnswer = Integer.parseInt(answer);
            } catch (NumberFormatException e) {
                answer = "";
            }
        } while (answer.isEmpty() || numberFromAnswer < 1);
        return numberFromAnswer;
    }
}
