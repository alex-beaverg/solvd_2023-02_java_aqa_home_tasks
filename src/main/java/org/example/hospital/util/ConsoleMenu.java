package org.example.hospital.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.hospital.custom_exceptions.*;
import org.example.hospital.data.HardCodeObjects;
import org.example.hospital.people.*;
import org.example.hospital.structure.Department;
import org.example.hospital.structure.Service;
import org.example.hospital.structure.VipService;
import org.example.hospital.structure.accounting.Accounting;
import org.example.hospital.util.menu_enums.*;

import java.util.ArrayList;
import java.util.Scanner;

public final class ConsoleMenu {
    private final static Logger LOGGER_LN;
    private final static Logger LOGGER;
    private final static Logger LN_LOGGER_LN;
    private final static Logger LOGGER_TO_CONSOLE_AND_FILE;
    private final HardCodeObjects objects;
    private Patient patient;
    private Employee doctor;
    private final Scanner scanner;

    static {
        LOGGER_LN = LogManager.getLogger("InsteadOfSOUT_ln");
        LOGGER = LogManager.getLogger("InsteadOfSOUT");
        LN_LOGGER_LN = LogManager.getLogger("ln_InsteadOfSOUT_ln");
        LOGGER_TO_CONSOLE_AND_FILE = LogManager.getLogger("Errors_To_Console_And_File");
    }

    {
        objects = new HardCodeObjects();
        scanner = new Scanner(System.in);
    }

    public void runApp() {
        objects.fillArrays();
        LN_LOGGER_LN.info("Welcome to the " + objects.hospital);
        runMainMenu();
    }

    private int runAnyMenu(String title, IMenu[] menuItems) {
        int index = 1;
        LN_LOGGER_LN.info(title);
        for (IMenu item : menuItems) {
            LOGGER_LN.info("[" + index + "] - " + item.getTitle());
            index++;
        }
        int answer;
        do {
            try {
                answer = requestingInfoWithChoice("Enter the menu item number: ", index - 1);
                break;
            } catch (EmptyInputException | MenuItemNumberOutOfBoundsException e) {
                LOGGER_TO_CONSOLE_AND_FILE.error(e.getMessage());
            } catch (NumberFormatException e) {
                LOGGER_TO_CONSOLE_AND_FILE.error("[NumberFormatException]: Entered data is not a number!");
            }
        } while (true);
        return answer;
    }

    private ConsoleMenu tearDown() {
        scanner.close();
        LOGGER_LN.info("Good bye!");
        return null;
    }

    private ConsoleMenu runMainMenu() {
        int answer = runAnyMenu("Main menu:", MainMenu.values());
        switch (answer) {
            case (1) -> {
                LN_LOGGER_LN.info("All departments in hospital:");
                for (Department department : objects.hospital.getDepartments()) {
                    LOGGER_LN.info("- " + department);
                }
                return runMainMenu();
            }
            case (2) -> {
                return runDoctorsMenu();
            }
            case (3) -> {
                return runPatientsMenu();
            }
            default -> {
                return tearDown();
            }
        }
    }

    private ConsoleMenu runDoctorsMenu() {
        int answer = runAnyMenu("Doctors menu:", DoctorsMenu.values());
        if (answer == 1) {
            doctor = showDoctors();
            return runDoctorMenu();
        } else if (answer == 2) {
            return runMainMenu();
        } else {
            return tearDown();
        }
    }

    private ConsoleMenu runDoctorMenu() {
        int answer = runAnyMenu("Doctor (" + doctor.getFullName() + ") menu:", DoctorMenu.values());
        switch (answer) {
            case (1) -> {
                LN_LOGGER_LN.info(doctor);
                return runDoctorMenu();
            }
            case (2) -> {
                LN_LOGGER_LN.info(Accounting.getPayslip(doctor));
                return runDoctorMenu();
            }
            case (3) -> {
                return runDoctorsMenu();
            }
            case (4) -> {
                return runMainMenu();
            }
            default -> {
                return tearDown();
            }
        }
    }

    private ConsoleMenu runPatientsMenu() {
        int answer = runAnyMenu("Patients menu:", PatientsMenu.values());
        switch (answer) {
            case (1) -> {
                patient = findExistPatient();
                if (patient != null) {
                    return runPatientMenu();
                } else {
                    return runPatientsMenu();
                }
            }
            case (2) -> {
                patient = registerNewPatient();
                LOGGER_LN.info("New patient (" + patient.getFullName() + ") was registered");
                patient = runComplaintsMenu();
                return runPatientMenu();
            }
            case (3) -> {
                patient = choosePatient();
                LOGGER_LN.info("Patient (" + patient.getFullName() + ") was chosen");
                return runPatientMenu();
            }
            case (4) -> {
                return runMainMenu();
            }
            default -> {
                return tearDown();
            }
        }
    }

    private Patient findExistPatient() {
        String fullName;
        do {
            try {
                fullName = requestingInfoString("\nEnter patient full name for searching: ");
                break;
            } catch (EmptyInputException | StringFormatException e) {
                LOGGER_TO_CONSOLE_AND_FILE.error(e.getMessage());
            }
        } while (true);
        for (Patient existPatient: objects.hospital.getPatients()) {
            if ((existPatient.getFirstName() + " " + existPatient.getLastName()).equalsIgnoreCase(fullName)) {
                LOGGER_LN.info("Patient " + existPatient.getFullName() + " was found and chosen");
                return existPatient;
            }
        }
        LOGGER_LN.info("Patient " + fullName + " was not found. Try it again");
        return null;
    }

    private ConsoleMenu runPatientMenu() {
        int answer = runAnyMenu("Patient (" + patient.getFullName() + ") menu:", PatientMenu.values());
        switch (answer) {
            case (1) -> {
                patient = changeDoctor();
                return runPatientMenu();
            }
            case (2) -> {
                patient = deleteVipService();
                return runPatientMenu();
            }
            case (3) -> {
                patient = runVipServiceMenu();
                return runPatientMenu();
            }
            case (4) -> {
                LN_LOGGER_LN.info(patient);
                return runPatientMenu();
            }
            case (5) -> {
                return runPatientsMenu();
            }
            case (6) -> {
                return runMainMenu();
            }
            default -> {
                return tearDown();
            }
        }
    }

    private Patient deleteVipService() {
        do {
            if (patient.getVipServices().size() > 0) {
                int index = 1;
                LN_LOGGER_LN.info("All your available VIP services to delete:");
                for (VipService vipService : patient.getVipServices()) {
                    LOGGER_LN.info("[" + index + "] - " + vipService.getTitle());
                    index++;
                }
                int answer;
                do {
                    try {
                        answer = requestingInfoWithChoice("Enter number of VIP service to delete it: ", index - 1);
                        break;
                    } catch (EmptyInputException | MenuItemNumberOutOfBoundsException e) {
                        LOGGER_TO_CONSOLE_AND_FILE.error(e.getMessage());
                    } catch (NumberFormatException e) {
                        LOGGER_TO_CONSOLE_AND_FILE.error("[NumberFormatException]: Entered data is not a number!");
                    }
                } while (true);
                VipService vipServiceToDelete = patient.getVipServices().get(answer - 1);
                patient.deleteVipService(vipServiceToDelete);
                patient.getDoctor().deleteVipService(vipServiceToDelete);
                LOGGER_LN.info("This VIP service (" + vipServiceToDelete.getTitle() + ") was deleted from patient");
            } else {
                LOGGER_LN.info("The patient has no VIP services");
            }
            String answer;
            do {
                try {
                    answer = requestingInfoWithYesOrNo("Do you want to delete another VIP service? (y/n): ");
                    break;
                } catch (EmptyInputException | YesOrNoException e) {
                    LOGGER_TO_CONSOLE_AND_FILE.error(e.getMessage());
                }
            } while (true);
            if (answer.equals("n")) {
                LOGGER_LN.info("OK!");
                break;
            }
        } while (true);
        return patient;
    }

    private Patient runVipServiceMenu() {
        do {
            if (patient.getVipServices().size() < VipService.values().length) {
                int index = 1;
                ArrayList<VipService> tempList = new ArrayList<>();
                LN_LOGGER_LN.info("All available VIP services:");
                for (VipService vipService: VipService.values()) {
                    if (!patient.getVipServices().contains(vipService)) {
                        LOGGER_LN.info("[" + index + "] - " + vipService.getTitle());
                        tempList.add(vipService);
                        index++;
                    }
                }
                int answer;
                do {
                    try {
                        answer = requestingInfoWithChoice("Enter number of VIP service to add it: ", index - 1);
                        break;
                    } catch (EmptyInputException | MenuItemNumberOutOfBoundsException e) {
                        LOGGER_TO_CONSOLE_AND_FILE.error(e.getMessage());
                    } catch (NumberFormatException e) {
                        LOGGER_TO_CONSOLE_AND_FILE.error("[NumberFormatException]: Entered data is not a number!");
                    }
                } while (true);
                VipService vipServiceToAdd = tempList.get(answer - 1);
                patient.addVipService(vipServiceToAdd);
                patient.getDoctor().addVipService(vipServiceToAdd);
                LOGGER_LN.info("This VIP service (" + vipServiceToAdd.getTitle() + ") was added to patient");
            } else {
                LOGGER_LN.info("The patient has all VIP services");
            }
            String answer;
            do {
                try {
                    answer = requestingInfoWithYesOrNo("Do you want to choose another VIP service? (y/n): ");
                    break;
                } catch (EmptyInputException | YesOrNoException e) {
                    LOGGER_TO_CONSOLE_AND_FILE.error(e.getMessage());
                }
            } while (true);
            if (answer.equals("n")) {
                LOGGER_LN.info("OK!");
                break;
            }
        } while (true);
        return patient;
    }

    private Employee showDoctors() {
        int index = 1;
        LN_LOGGER_LN.info("All doctors in the hospital:");
        for (Employee doctor: objects.hospital.getEmployeesBySpecialistClass(2)) {
            LOGGER_LN.info("[" + index + "] - " + doctor.getPersonToPrintInList());
            index++;
        }
        int answer;
        do {
            try {
                answer = requestingInfoWithChoice("Enter number of doctor to choose him: ", index - 1);
                break;
            } catch (EmptyInputException | MenuItemNumberOutOfBoundsException e) {
                LOGGER_TO_CONSOLE_AND_FILE.error(e.getMessage());
            } catch (NumberFormatException e) {
                LOGGER_TO_CONSOLE_AND_FILE.error("[NumberFormatException]: Entered data is not a number!");
            }
        } while (true);
        doctor = objects.hospital.getEmployeesBySpecialistClass(2).get(answer - 1);
        LOGGER_LN.info("Doctor " + doctor.getFullName() + " was chosen");
        return doctor;
    }

    private Patient choosePatient() {
        int index = 1;
        LN_LOGGER_LN.info("All patients in the hospital:");
        for (Patient existPatient: objects.hospital.getPatients()) {
            LOGGER_LN.info("[" + index + "] - " + existPatient.getPersonToPrintInList());
            index++;
        }
        int answer;
        do {
            try {
                answer = requestingInfoWithChoice("Enter number of patient to choose him: ", index - 1);
                break;
            } catch (EmptyInputException | MenuItemNumberOutOfBoundsException e) {
                LOGGER_TO_CONSOLE_AND_FILE.error(e.getMessage());
            } catch (NumberFormatException e) {
                LOGGER_TO_CONSOLE_AND_FILE.error("[NumberFormatException]: Entered data is not a number!");
            }
        } while (true);
        return objects.hospital.getPatients().get(answer - 1);
    }

    private Patient assignDoctor() {
        int index = 1;
        ArrayList<Employee> tempList = new ArrayList<>();
        LN_LOGGER_LN.info("All available doctors in your department:");
        for (Employee doctor: patient.getDepartment().getEmployeesBySpecialistClass(2)) {
            if (doctor != patient.getDoctor()) {
                LOGGER_LN.info("[" + index + "] - " + doctor.getPersonToPrintInList());
                tempList.add(doctor);
                index++;
            }
        }
        int answer;
        do {
            try {
                answer = requestingInfoWithChoice("Enter number of doctor to choose him: ", index - 1);
                break;
            } catch (EmptyInputException | MenuItemNumberOutOfBoundsException e) {
                LOGGER_TO_CONSOLE_AND_FILE.error(e.getMessage());
            } catch (NumberFormatException e) {
                LOGGER_TO_CONSOLE_AND_FILE.error("[NumberFormatException]: Entered data is not a number!");
            }
        } while (true);
        patient.setDoctor(tempList.get(answer - 1));
        LOGGER_LN.info("Your doctor (" + patient.getDoctor().getFirstName() + " " + patient.getDoctor().getLastName() + ") was assigned");
        return patient;
    }

    private Patient changeDoctor() {
        int index = 1;
        ArrayList<Employee> tempList = new ArrayList<>();
        LN_LOGGER_LN.info("All available doctors in your department:");
        for (Employee doctor: patient.getDepartment().getEmployeesBySpecialistClass(2)) {
            if (doctor != patient.getDoctor()) {
                LOGGER_LN.info("[" + index + "] - " + doctor.getPersonToPrintInList());
                tempList.add(doctor);
                index++;
            }
        }
        int answer;
        do {
            try {
                answer = requestingInfoWithChoice("Enter number of doctor to choose him: ", index - 1);
                break;
            } catch (EmptyInputException | MenuItemNumberOutOfBoundsException e) {
                LOGGER_TO_CONSOLE_AND_FILE.error(e.getMessage());
            } catch (NumberFormatException e) {
                LOGGER_TO_CONSOLE_AND_FILE.error("[NumberFormatException]: Entered data is not a number!");
            }
        } while (true);
        for (Service service: patient.getServices()) {
            patient.getDoctor().deleteService(service);
        }
        for (VipService vipService: patient.getVipServices()) {
            patient.getDoctor().deleteVipService(vipService);
        }
        String fullNameOfOldDoctor = patient.getDoctor().getFullName();
        patient.getDoctor().deletePatient(patient);
        patient.setDoctor(tempList.get(answer - 1));
        patient.getDoctor().addPatient(patient);
        for (Service service: patient.getServices()) {
            patient.getDoctor().addService(service);
        }
        for (VipService vipService: patient.getVipServices()) {
            patient.getDoctor().addVipService(vipService);
        }
        LOGGER_LN.info("Dr. " + fullNameOfOldDoctor + " has been replaced by dr. " + patient.getDoctor().getFullName());
        return patient;
    }

    private Patient registerNewPatient(){
        Patient newPatient = Creator.setPatient();
        Address address = Creator.setAddress();
        LN_LOGGER_LN.info("Registration of a new patient");
        do {
            try {
                newPatient.setFirstName(requestingInfoString("Enter your first name: "));
                break;
            } catch (EmptyInputException | StringFormatException e) {
                LOGGER_TO_CONSOLE_AND_FILE.error(e.getMessage());
            }
        } while (true);
        do {
            try {
                newPatient.setLastName(requestingInfoString("Enter your last name: "));
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
                address.setCity(requestingInfoString("Enter your city: "));
                break;
            } catch (EmptyInputException | StringFormatException e) {
                LOGGER_TO_CONSOLE_AND_FILE.error(e.getMessage());
            }
        } while (true);
        do {
            try {
                address.setStreet(requestingInfoString("Enter your street: "));
                break;
            } catch (EmptyInputException | StringFormatException e) {
                LOGGER_TO_CONSOLE_AND_FILE.error(e.getMessage());
            }
        } while (true);
        do {
            try {
                address.setHouseNumber(requestingInfoInt("Enter your house number: "));
                break;
            } catch (EmptyInputException | NegativeNumberException e) {
                LOGGER_TO_CONSOLE_AND_FILE.error(e.getMessage());
            } catch (NumberFormatException e) {
                LOGGER_TO_CONSOLE_AND_FILE.error("[NumberFormatException]: Entered data is not a number!");
            }
        } while (true);
        do {
            try {
                address.setFlatNumber(requestingInfoInt("Enter your flat number: "));
                break;
            } catch (EmptyInputException | NegativeNumberException e) {
                LOGGER_TO_CONSOLE_AND_FILE.error(e.getMessage());
            } catch (NumberFormatException e) {
                LOGGER_TO_CONSOLE_AND_FILE.error("[NumberFormatException]: Entered data is not a number!");
            }
        } while (true);
        newPatient.setAddress(address);
        objects.hospital.addPatient(newPatient);
        return newPatient;
    }

    private int getAgeFromConsole() throws AgeException {
        do {
            try {
                int age = requestingInfoInt("Enter your age: ");
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

    private Patient runComplaintsMenu() {
        int answer = runAnyMenu("Complaints menu:", ComplaintsMenu.values());
        patient.setDiagnosis(getDiagnose(answer));
        LOGGER_LN.info("Diagnosis (" + patient.getDiagnosis().getTitle() + ") was made");
        String answerString;
        do {
            try {
                answerString = requestingInfoWithYesOrNo("\nDo you want to assign doctor? (y/n): ");
                break;
            } catch (EmptyInputException | YesOrNoException e) {
                LOGGER_TO_CONSOLE_AND_FILE.error(e.getMessage());
            }
        } while (true);
        if (answerString.equals("y")) {
            patient = assignDoctor();
        } else {
            LOGGER_LN.info("Your doctor (" + patient.getDoctor().getFullName() + ") was assigned by hospital automatically");
        }
        patient = runServiceMenu();
        do {
            try {
                answerString = requestingInfoWithYesOrNo("\nDo you want to choose VIP service? (y/n): ");
                break;
            } catch (EmptyInputException | YesOrNoException e) {
                LOGGER_TO_CONSOLE_AND_FILE.error(e.getMessage());
            }
        } while (true);
        if (answerString.equals("y")) {
            patient = runVipServiceMenu();
        } else {
            LOGGER_LN.info("OK!");
        }
        patient.getDoctor().addPatient(patient);
        return patient;
    }

    private Patient runServiceMenu() {
        do {
            if (patient.getServices().size() < Service.values().length) {
                int index = 1;
                ArrayList<Service> tempList = new ArrayList<>();
                LN_LOGGER_LN.info("All available services:");
                for (Service service : Service.values()) {
                    if (!patient.getServices().contains(service)) {
                        LOGGER_LN.info("[" + index + "] - " + service.getTitle());
                        tempList.add(service);
                        index++;
                    }
                }
                int answer;
                do {
                    try {
                        answer = requestingInfoWithChoice("Enter number of service to choose it: ", index - 1);
                        break;
                    } catch (EmptyInputException | MenuItemNumberOutOfBoundsException e) {
                        LOGGER_TO_CONSOLE_AND_FILE.error(e.getMessage());
                    } catch (NumberFormatException e) {
                        LOGGER_TO_CONSOLE_AND_FILE.error("[NumberFormatException]: Entered data is not a number!");
                    }
                } while (true);
                Service serviceToAdd = tempList.get(answer - 1);
                patient.addService(serviceToAdd);
                patient.getDoctor().addService(serviceToAdd);
                LOGGER_LN.info("This service (" + serviceToAdd.getTitle() + ") was added to patient");
            } else {
                LOGGER_LN.info("The patient has all services");
            }
            String answer;
            do {
                try {
                    answer = requestingInfoWithYesOrNo("Do you want to choose another service? (y/n): ");
                    break;
                } catch (EmptyInputException | YesOrNoException e) {
                    LOGGER_TO_CONSOLE_AND_FILE.error(e.getMessage());
                }
            } while (true);
            if (answer.equals("n")) {
                LOGGER_LN.info("OK!");
                break;
            }
        } while (true);
        return patient;
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

    private String requestingInfoWithYesOrNo(String text) throws EmptyInputException, YesOrNoException {
        LOGGER.info(text);
        String answer = scanner.nextLine();
        if (answer.isEmpty()) {
            throw new EmptyInputException("[EmptyInputException]: Entered data can not be empty!");
        }
        if (!answer.equals("y") && !answer.equals("n")) {
            throw new YesOrNoException("[YesOrNoException]: Entered data must be equal to 'y' or 'n'!");
        }
        return answer;
    }

    private int requestingInfoWithChoice(String text, int menuItemsNumber)
            throws EmptyInputException, NumberFormatException, MenuItemNumberOutOfBoundsException {
        LOGGER.info(text);
        String answer = scanner.nextLine();
        if (answer.isEmpty()) {
            throw new EmptyInputException("[EmptyInputException]: Entered data can not be empty!");
        }
        int numberFromAnswer = Integer.parseInt(answer);
        if (numberFromAnswer < 1 || numberFromAnswer > menuItemsNumber) {
            throw new MenuItemNumberOutOfBoundsException("[MenuItemNumberOutOfBoundsException]: Entered data " +
                    "must be equal to some menu item!");
        }
        return numberFromAnswer;
    }

    private String requestingInfoString(String text) throws EmptyInputException, StringFormatException {
        LOGGER.info(text);
        String answer = scanner.nextLine();
        if (answer.isEmpty()) {
            throw new EmptyInputException("[EmptyInputException]: Entered data can not be empty!");
        }
        if (!answer.matches("^[a-zA-Zа-яёА-ЯЁ]+[\\s-]?[a-zA-Zа-яёА-ЯЁ]+$")) {
            throw new StringFormatException("[StringFormatException]: Entered data is not a letter character!");
        }
        return answer;
    }

    private int requestingInfoInt(String text)
            throws EmptyInputException, NumberFormatException, NegativeNumberException {
        LOGGER.info(text);
        String answer = scanner.nextLine();
        if (answer.isEmpty()) {
            throw new EmptyInputException("[EmptyInputException]: Entered data can not be empty!");
        }
        int numberFromAnswer = Integer.parseInt(answer);
        if (numberFromAnswer < 0) {
            throw new NegativeNumberException("[NegativeNumberException]: Entered data can not be negative");
        }
        return numberFromAnswer;
    }
}
