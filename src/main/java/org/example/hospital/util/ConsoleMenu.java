package org.example.hospital.util;

import org.example.hospital.data.HardCodeObjects;
import org.example.hospital.people.*;
import org.example.hospital.structure.Department;
import org.example.hospital.structure.Service;
import org.example.hospital.structure.VipService;
import org.example.hospital.util.menu_enums.*;

import java.util.ArrayList;
import java.util.Scanner;

public final class ConsoleMenu {
    private final HardCodeObjects objects = new HardCodeObjects();
    private Patient patient;
    private final Scanner scanner = new Scanner(System.in);

    public void runApp() {
        objects.fillArrays();
        System.out.println("\nWelcome to the " + objects.hospital);
        runMainMenu();
    }

    private int runAnyMenu(String title, IMenu[] menuItems) {
        int index = 1;
        System.out.println(title);
        for (IMenu item : menuItems) {
            System.out.println("[" + index + "] - " + item.getTitle());
            index++;
        }
        return requestingInfoWithChoice("Enter the menu item number: ", index - 1);
    }

    private void runMainMenu() {
        int answer = runAnyMenu("Main menu:", MainMenu.values());
        switch (answer) {
            case (1) -> {
                System.out.println("All departments in hospital:");
                for (Department department : objects.hospital.getDepartments()) {
                    System.out.println("- " + department);
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
        int answer = runAnyMenu("Doctors menu:", DoctorsMenu.values());
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
        int answer = runAnyMenu("Patients menu:", PatientsMenu.values());
        switch (answer) {
            case (1) -> {
                patient = findExistPatient();
                if (patient != null) {
                    runPatientMenu();
                } else {
                    runPatientsMenu();
                }
            }
            case (2) -> {
                patient = registerNewPatient();
                System.out.println("New patient (" + patient.getFullName() + ") was registered");
                runComplaintsMenu();
                System.out.println(patient);
                runPatientMenu();
            }
            case (3) -> {
                patient = choosePatient();
                System.out.println("Patient (" + patient.getFullName() + ") was chosen!");
                runPatientMenu();
            }
            case (4) -> runMainMenu();
            default -> {
                scanner.close();
                System.out.println("Good bye!");
            }
        }
    }

    private Patient findExistPatient() {
        String fullName = requestingInfoString("Enter patient full name for searching: ");
        for (Patient existPatient: objects.hospital.getPatients()) {
            if ((existPatient.getFirstName() + " " + existPatient.getLastName()).equals(fullName)) {
                System.out.println("Patient " + fullName + " was found and chosen:");
                return existPatient;
            }
        }
        System.out.println("Patient " + fullName + " was not found. Try it again");
        return null;
    }

    private void runPatientMenu() {
        int answer = runAnyMenu("Patient menu:", PatientMenu.values());
        switch (answer) {
            case (1) -> {
                changeDoctor();
                runPatientMenu();
            }
            case (2) -> {
                deleteVipService();
                runPatientMenu();
            }
            case (3) -> {
                runVipServiceMenu();
                runPatientMenu();
            }
            case (4) -> {
                System.out.println(patient);
                runPatientMenu();
            }
            case (5) -> runMainMenu();
            default -> {
                scanner.close();
                System.out.println("Good bye!");
            }
        }
    }

    private void deleteVipService() {
        do {
            if (patient.getVipServices().size() > 0) {
                int index = 1;
                System.out.println("All your available VIP services to delete:");
                for (VipService vipService : patient.getVipServices()) {
                    System.out.println("[" + index + "] - " + vipService.getTitle());
                    index++;
                }
                int answer = requestingInfoWithChoice("Enter number of VIP service to delete it: ", index - 1);
                VipService vipServiceToDelete = patient.getVipServices().get(answer - 1);
                patient.deleteVipService(vipServiceToDelete);
                patient.getDoctor().deleteVipService(vipServiceToDelete);
                System.out.println("This VIP service (" + vipServiceToDelete.getTitle() + ") was deleted from patient and his doctor");
            } else {
                System.out.println("The patient has no VIP services");
            }
            String answer = requestingInfoWithYesOrNo("Do you want to delete another VIP service? (y/n): ");
            if (answer.equals("n")) {
                System.out.println("OK!");
                break;
            }
        } while (true);
    }

    private void runVipServiceMenu() {
        do {
            if (patient.getVipServices().size() < VipService.values().length) {
                int index = 1;
                ArrayList<VipService> tempList = new ArrayList<>();
                System.out.println("All available VIP services:");
                for (VipService vipService: VipService.values()) {
                    if (!patient.getVipServices().contains(vipService)) {
                        System.out.println("[" + index + "] - " + vipService.getTitle());
                        tempList.add(vipService);
                        index++;
                    }
                }
                int answer = requestingInfoWithChoice("Enter number of VIP service to add it: ", index - 1);
                VipService vipServiceToAdd = tempList.get(answer - 1);
                patient.addVipService(vipServiceToAdd);
                patient.getDoctor().addVipService(vipServiceToAdd);
                System.out.println("This VIP service (" + vipServiceToAdd.getTitle() + ") was added to patient");
            } else {
                System.out.println("The patient has all VIP services");
            }
            String answer = requestingInfoWithYesOrNo("Do you want to choose another VIP service? (y/n): ");
            if (answer.equals("n")) {
                System.out.println("OK!");
                break;
            }
        } while (true);
    }

    private void showDoctors() {
        int index = 1;
        System.out.println("All doctors in the hospital:");
        for (Employee doctor: objects.hospital.getEmployeesBySpecialistClass(2)) {
            System.out.println("[" + index + "] - " + doctor.getPersonToPrintInList());
            index++;
        }
        int answer = requestingInfoWithChoice("Enter number of doctor to show more information: ", index - 1);
        System.out.println(objects.hospital.getEmployeesBySpecialistClass(2).get(answer - 1));
    }

    private Patient choosePatient() {
        int index = 1;
        System.out.println("All patients in the hospital:");
        for (Patient existPatient: objects.hospital.getPatients()) {
            System.out.println("[" + index + "] - " + existPatient.getPersonToPrintInList());
            index++;
        }
        int answer = requestingInfoWithChoice("Enter number of patient to choose him: ", index - 1);
        System.out.println(objects.hospital.getPatients().get(answer - 1));
        return objects.hospital.getPatients().get(answer - 1);
    }

    private void assignDoctor() {
        int index = 1;
        ArrayList<Employee> tempList = new ArrayList<>();
        System.out.println("All available doctors in your department:");
        for (Employee doctor: patient.getDepartment().getEmployeesBySpecialistClass(2)) {
            if (doctor != patient.getDoctor()) {
                System.out.println("[" + index + "] - " + doctor.getPersonToPrintInList());
                tempList.add(doctor);
                index++;
            }
        }
        int answer = requestingInfoWithChoice("Enter number of doctor to choose him: ", index - 1);
        patient.setDoctor(tempList.get(answer - 1));
        System.out.println("Your doctor (" + patient.getDoctor().getFirstName() + " " + patient.getDoctor().getLastName() + ") was assigned!");
    }

    private void changeDoctor() {
        int index = 1;
        ArrayList<Employee> tempList = new ArrayList<>();
        System.out.println("All available doctors in your department:");
        for (Employee doctor: patient.getDepartment().getEmployeesBySpecialistClass(2)) {
            if (doctor != patient.getDoctor()) {
                System.out.println("[" + index + "] - " + doctor.getPersonToPrintInList());
                tempList.add(doctor);
                index++;
            }
        }
        int answer = requestingInfoWithChoice("Enter number of doctor to choose him: ", index - 1);
        deleteAllServicesFromDoctor();
        Employee oldDoctor = patient.getDoctor();
        patient.getDoctor().deletePatient(patient);
        patient.setDoctor(tempList.get(answer - 1));
        patient.getDoctor().addPatient(patient);
        addAllServicesToDoctor();
        System.out.println("Dr. " + oldDoctor.getFullName() + " has been replaced by dr. " + patient.getDoctor().getFullName());
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

    private void runComplaintsMenu() {
        int answer = runAnyMenu("Complaints menu:", ComplaintsMenu.values());
        patient.setDiagnosis(getDiagnose(answer - 1));
        String answerString = requestingInfoWithYesOrNo("Do you want to assign doctor? (y/n): ");
        if (answerString.equals("y")) {
            assignDoctor();
        } else {
            System.out.println("Your doctor (" + patient.getDoctor().getFullName() + ") was assigned by hospital automatically");
        }
        runServiceMenu();
        answerString = requestingInfoWithYesOrNo("Do you want to choose VIP service? (y/n): ");
        if (answerString.equals("y")) {
            runVipServiceMenu();
        } else {
            System.out.println("OK!");
        }
        patient.getDoctor().addPatient(patient);
    }

    private void runServiceMenu() {
        do {
            if (patient.getServices().size() < Service.values().length) {
                int index = 1;
                ArrayList<Service> tempList = new ArrayList<>();
                System.out.println("All available services:");
                for (Service service : Service.values()) {
                    if (!patient.getServices().contains(service)) {
                        System.out.println("[" + index + "] - " + service.getTitle());
                        tempList.add(service);
                        index++;
                    }
                }
                int answer = requestingInfoWithChoice("Enter number of service to choose it: ", index - 1);
                Service serviceToAdd = tempList.get(answer - 1);
                patient.addService(serviceToAdd);
                patient.getDoctor().addService(serviceToAdd);
                System.out.println("This service (" + serviceToAdd.getTitle() + ") was added to patient");
            } else {
                System.out.println("The patient has all services");
            }
            String answer = requestingInfoWithYesOrNo("Do you want to choose another service? (y/n): ");
            if (answer.equals("n")) {
                System.out.println("OK!");
                break;
            }
        } while (true);
    }

    private void addAllServicesToDoctor() {
        for (Service service: patient.getServices()) {
            patient.getDoctor().addService(service);
        }
        for (VipService vipService: patient.getVipServices()) {
            patient.getDoctor().addVipService(vipService);
        }
    }

    private void deleteAllServicesFromDoctor() {
        for (Service service: patient.getServices()) {
            patient.getDoctor().deleteService(service);
        }
        for (VipService vipService: patient.getVipServices()) {
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
