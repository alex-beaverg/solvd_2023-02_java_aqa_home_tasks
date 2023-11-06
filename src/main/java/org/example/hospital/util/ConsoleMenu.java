package org.example.hospital.util;

import org.example.hospital.data.HardCodeObjects;
import org.example.hospital.people.*;
import org.example.hospital.structure.Department;
import org.example.hospital.structure.Service;
import org.example.hospital.structure.VipService;

import java.util.Scanner;

public final class ConsoleMenu {
    private final HardCodeObjects objects = new HardCodeObjects();
    private Patient patient;
    Scanner scanner = new Scanner(System.in);

    public void runApp() {
        objects.fillArrays();
        System.out.println("\nWelcome to the " + objects.hospital);
        runMainMenu();
    }

    private void runMainMenu() {
        int answer = requestingInfoWithChoice(
                """
                        Choose an action:
                        [1] - Show departments
                        [2] - Doctors menu actions
                        [3] - Patients menu actions
                        [4] - Exit
                        """, 4);
        switch (answer) {
            case (1) -> {
                System.out.println("All departments in hospital:");
                for (Department department : objects.hospital.getDepartments()) {
                    System.out.println(department);
                }
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

    private void runDoctorsMenu() {
        int answer = requestingInfoWithChoice(
                """
                        Choose an action:
                        [1] - Show all doctors
                        [2] - Go to main menu
                        [3] - Exit
                        """, 3);
        if (answer == 1) {
            showDoctors();
            runDoctorsMenu();
        } else if (answer == 2) {
            runMainMenu();
        } else {
            scanner.close();
            System.out.println("Good bye!");
        }
    }

    private void runPatientsMenu() {
        int answer = requestingInfoWithChoice(
                """
                        Choose an action:
                        [1] - Find and choose the patient in registry
                        [2] - Register new patient
                        [3] - Show all patients and choose one of them
                        [4] - Go to main menu
                        [5] - Exit
                        """, 5);
        switch (answer) {
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
                patient = choosePatient();
                System.out.println("Your patient (" + patient.getFirstName() + " " + patient.getLastName() + ") was chosen!");
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
        int answer = requestingInfoWithChoice(
                """
                        Choose an action:
                        [1] - Change doctor
                        [2] - Go to main menu
                        [3] - Exit
                        """, 3);
        if (answer == 1) {
            changeDoctor();
            runPatientsSubmenu();
        } else if (answer == 2) {
            runMainMenu();
        } else {
            scanner.close();
            System.out.println("Good bye!");
        }
    }

    private void changeDoctor() {
        int index = 1;
        System.out.println("Choose the doctor from your department:");
        for (Employee doctor : patient.getDepartment().getEmployeesBySpecialistClass(2)) {
            System.out.println("[" + index + "] - " + doctor.getPersonToPrintInList());
            index++;
        }
        int answer = requestingInfoWithChoice("Enter number of doctor to choose him: ", index - 1);
        deleteServicesFromDoctor();
        patient.getDoctor().deletePatient(patient);
        patient.setDoctor(patient.getDepartment().getEmployeesBySpecialistClass(2).get(answer - 1));
        patient.getDoctor().addPatient(patient);
        addServicesToDoctor();
        System.out.println("Doctor was changed");
    }

    private void showDoctors() {
        int index = 1;
        System.out.println("All doctors in the hospital:");
        for (Employee doctor : objects.hospital.getEmployeesBySpecialistClass(2)) {
            System.out.println("[" + index + "] - " + doctor.getPersonToPrintInList());
            index++;
        }
        int answer = requestingInfoWithChoice("Enter number of doctor to show more information: ", index - 1);
        System.out.println(objects.hospital.getEmployeesBySpecialistClass(2).get(answer - 1));
    }

    private Patient choosePatient() {
        int index = 1;
        System.out.println("All patients in the hospital:");
        for (Patient existPatient : objects.hospital.getPatients()) {
            System.out.println("[" + index + "] - " + existPatient.getPersonToPrintInList());
            index++;
        }
        int answer = requestingInfoWithChoice("Enter number of patient to choose him: ", index - 1);
        System.out.println(objects.hospital.getPatients().get(answer - 1));
        return objects.hospital.getPatients().get(answer - 1);
    }

    private void vipServiceMenu() {
        do {
            VipService vipService = getVipService(requestingInfoWithChoice(
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

    private void chooseDoctor() {
        int index = 1;
        System.out.println("All doctors in your department:");
        for (Employee employee : patient.getDepartment().getEmployeesBySpecialistClass(2)) {
            System.out.println("[" + index + "] - " + employee.getPersonToPrintInList());
            index++;
        }
        int answer = requestingInfoWithChoice("Enter number of doctor to choose him: ", index - 1);
        patient.setDoctor(patient.getDepartment().getEmployeesBySpecialistClass(2).get(answer - 1));
        System.out.println("Your doctor (" + patient.getDoctor().getFirstName() + " " + patient.getDoctor().getLastName() + ") was chosen!");
    }

    private Patient findExistPatient() {
        String fullName = requestingInfoString("Enter patient full name for searching: ");
        for (Patient existPatient : objects.hospital.getPatients()) {
            if ((existPatient.getFirstName() + " " + existPatient.getLastName()).equals(fullName)) {
                System.out.println("Patient " + fullName + " was found and chosen:");
                System.out.println(existPatient);
                return existPatient;
            }
        }
        System.out.println("Patient " + fullName + " was not found.");
        int answer = requestingInfoWithChoice(
                """
                        Choose an action:
                        [1] - Try to find and choose the patient again
                        [2] - Register new patient
                        [3] - Show all patients to choose one of them
                        [4] - Go to main menu
                        [5] - Exit
                        """, 5);
        switch (answer) {
            case (1) -> {
                return findExistPatient();
            }
            case (2) -> {
                patient = registerNewPatient();
                System.out.println("New patient (" + patient.getFirstName() + " " + patient.getLastName() + ") was registered");
                toComplain();
                System.out.println(patient);
                return patient;
            }
            case (3) -> {
                return choosePatient();
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
        Patient newPatient = Creator.setPatient();
        Address address = Creator.setAddress();
        System.out.println("Registration of a new patient");
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
        patient.setDiagnosis(getDiagnose(requestingInfoWithChoice(
                """
                        Enter your complaint:
                        [1] - Cough
                        [2] - No smells
                        [3] - Broken bone
                        [4] - Unknown
                        """, 4)));
        serviceMenu();
        String answer = requestingInfoWithYesOrNo("Do you want to choose doctor? (y/n): ");
        if (answer.equals("y")) {
            chooseDoctor();
        } else {
            System.out.println("OK!");
        }
        answer = requestingInfoWithYesOrNo("Do you want to choose VIP service? (y/n): ");
        if (answer.equals("y")) {
            vipServiceMenu();
        } else {
            System.out.println("OK!");
        }
        addServicesToDoctor();
        patient.getDoctor().addPatient(patient);
    }

    private void serviceMenu() {
        do {
            Service service = getService(requestingInfoWithChoice(
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
    }

    private void addServicesToDoctor() {
        for (Service service : patient.getServices()) {
            patient.getDoctor().addService(service);
        }
        for (VipService vipService : patient.getVipServices()) {
            patient.getDoctor().addVipService(vipService);
        }
    }

    private void deleteServicesFromDoctor() {
        for (Service service : patient.getServices()) {
            patient.getDoctor().deleteService(service);
        }
        for (VipService vipService : patient.getVipServices()) {
            patient.getDoctor().deleteVipService(vipService);
        }
    }

    private Diagnosis getDiagnose(int number) {
        switch (number) {
            case (1) -> {
                patient.setDepartment(objects.therapeuticDept);
                patient.setDoctor(objects.therapeuticDept.getRandomEmployeeBySpecialistClass(2));
                patient.setNurse(objects.therapeuticDept.getRandomEmployeeBySpecialistClass(1));
                objects.therapeuticDept.addPatient(patient);
                return Diagnosis.FLU;
            }
            case (2) -> {
                patient.setDepartment(objects.therapeuticDept);
                patient.setDoctor(objects.therapeuticDept.getRandomEmployeeBySpecialistClass(2));
                patient.setNurse(objects.therapeuticDept.getRandomEmployeeBySpecialistClass(1));
                objects.therapeuticDept.addPatient(patient);
                return Diagnosis.COVID;
            }
            case (3) -> {
                patient.setDepartment(objects.surgeryDept);
                patient.setDoctor(objects.surgeryDept.getRandomEmployeeBySpecialistClass(2));
                patient.setNurse(objects.surgeryDept.getRandomEmployeeBySpecialistClass(1));
                objects.surgeryDept.addPatient(patient);
                return Diagnosis.BONE_FRACTURE;
            }
            default -> {
                patient.setDepartment(objects.therapeuticDept);
                patient.setDoctor(objects.therapeuticDept.getRandomEmployeeBySpecialistClass(2));
                patient.setNurse(objects.therapeuticDept.getRandomEmployeeBySpecialistClass(1));
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

    private VipService getVipService(int number) {
        return switch (number) {
            case (1) -> VipService.SEPARATE_ROOM;
            case (2) -> VipService.SPECIAL_FOOD;
            case (3) -> VipService.WALK_IN_SERVICE;
            default -> VipService.ENTERTAINMENT;
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

    private int requestingInfoWithChoice(String text, int menuItemsNumber) {
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
            if (answer.isEmpty() || numberFromAnswer < 1 || numberFromAnswer > menuItemsNumber) {
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
            if (answer.isEmpty() || numberFromAnswer < 1) {
                text = "Please, enter correct data: ";
            } else {
                break;
            }
        } while (true);
        return numberFromAnswer;
    }
}
