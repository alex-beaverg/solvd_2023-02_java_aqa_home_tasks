package org.example.hospital.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.hospital.custom_exceptions.*;
import org.example.hospital.data.Creator;
import org.example.hospital.people.*;
import org.example.hospital.structure.Department;
import org.example.hospital.structure.Hospital;
import org.example.hospital.structure.Service;
import org.example.hospital.structure.VipService;
import org.example.hospital.structure.accounting.Accounting;
import org.example.hospital.util.menu_enums.*;

import java.util.ArrayList;
import java.util.List;

public final class ConsoleMenu {
    private final static Logger LOGGER_LN;
    private final static Logger LN_LOGGER_LN;
    private final static Logger LOGGER_TO_CONSOLE_AND_FILE;
    private final Hospital hospital;

    static {
        LOGGER_LN = LogManager.getLogger("InsteadOfSOUT_ln");
        LN_LOGGER_LN = LogManager.getLogger("ln_InsteadOfSOUT_ln");
        LOGGER_TO_CONSOLE_AND_FILE = LogManager.getLogger("Errors_To_Console_And_File");
    }

    {
        hospital = Creator.generateHospital();
    }

    public void runApp() {
        LN_LOGGER_LN.info("Welcome to the " + hospital);
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
                answer = RequestMethods.requestingInfoWithChoice("Enter the menu item number: ", index - 1);
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
        RequestMethods.closeScanner();
        LOGGER_LN.info("Good bye!");
        return null;
    }

    private ConsoleMenu runMainMenu() {
        int answer = runAnyMenu("Main menu:", MainMenu.values());
        switch (answer) {
            case (1) -> {
                LN_LOGGER_LN.info("All departments in hospital:");
                for (Department department : hospital.getDepartments()) {
                    LOGGER_LN.info("- " + department);
                }
                return runMainMenu();
            }
            case (2) -> {
                LN_LOGGER_LN.info("All employees in hospital:");
                for (Employee employee : hospital.getEmployees()) {
                    LOGGER_LN.info("- " + employee.getPersonToPrintInList());
                }
                return runMainMenu();
            }
            case (3) -> {
                return runDoctorsMenu();
            }
            case (4) -> {
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
            return runDoctorMenu(hospital.showDoctors());
        } else if (answer == 2) {
            return runMainMenu();
        } else {
            return tearDown();
        }
    }

    private ConsoleMenu runDoctorMenu(Employee doctor) {
        int answer = runAnyMenu("Doctor (" + doctor.getFullName() + ") menu:", DoctorMenu.values());
        switch (answer) {
            case (1) -> {
                LN_LOGGER_LN.info(doctor);
                return runDoctorMenu(doctor);
            }
            case (2) -> {
                LN_LOGGER_LN.info(Accounting.getPayslip(doctor));
                return runDoctorMenu(doctor);
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
        Patient patient;
        int answer = runAnyMenu("Patients menu:", PatientsMenu.values());
        switch (answer) {
            case (1) -> {
                patient = hospital.findExistPatient();
                if (patient != null) {
                    return runPatientMenu(patient);
                } else {
                    return runPatientsMenu();
                }
            }
            case (2) -> {
                patient = hospital.registerNewPatient();
                LOGGER_LN.info("New patient (" + patient.getFullName() + ") was registered");
                return runComplaintsMenu(patient);
            }
            case (3) -> {
                patient = hospital.choosePatient();
                LOGGER_LN.info("Patient (" + patient.getFullName() + ") was chosen");
                return runPatientMenu(patient);
            }
            case (4) -> {
                return runMainMenu();
            }
            default -> {
                return tearDown();
            }
        }
    }

    private ConsoleMenu runPatientMenu(Patient patient) {
        int answer = runAnyMenu("Patient (" + patient.getFullName() + ") menu:", PatientMenu.values());
        switch (answer) {
            case (1) -> {
                return runPatientMenu(patient.changeDoctor());
            }
            case (2) -> {
                return runDeleteVipServiceMenu(patient);
            }
            case (3) -> {
                return runAddVipServiceMenu(patient);
            }
            case (4) -> {
                LN_LOGGER_LN.info(patient);
                return runPatientMenu(patient);
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

    private ConsoleMenu runComplaintsMenu(Patient patient) {
        int answer = runAnyMenu("Complaints menu:", ComplaintsMenu.values());
        patient.setDiagnosis(hospital.getDiagnose(patient, answer));
        LOGGER_LN.info("Diagnosis (" + patient.getDiagnosis().getTitle() + ") was made");
        String answerString;
        do {
            try {
                answerString = RequestMethods.requestingInfoWithYesOrNo("\nDo you want to assign doctor? (y/n): ");
                break;
            } catch (EmptyInputException | YesOrNoException e) {
                LOGGER_TO_CONSOLE_AND_FILE.error(e.getMessage());
            }
        } while (true);
        if (answerString.equals("y")) {
            patient = patient.assignDoctor();
        } else {
            LOGGER_LN.info("Your doctor (" + patient.getDoctor().getFullName() + ") was assigned by hospital automatically");
        }
        return runServiceMenu(patient);
    }

    private ConsoleMenu runServiceMenu(Patient patient) {
        String answerString;
        do {
            if (patient.getServices().size() < Service.values().length) {
                int index = 1;
                List<Service> tempList = new ArrayList<>();
                LN_LOGGER_LN.info("All available general services:");
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
                        answer = RequestMethods.requestingInfoWithChoice("Enter number of service to choose it: ", index - 1);
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
            do {
                try {
                    answerString = RequestMethods.requestingInfoWithYesOrNo("Do you want to choose another service? (y/n): ");
                    break;
                } catch (EmptyInputException | YesOrNoException e) {
                    LOGGER_TO_CONSOLE_AND_FILE.error(e.getMessage());
                }
            } while (true);
            if (answerString.equals("n")) {
                LOGGER_LN.info("OK!");
                break;
            }
        } while (true);
        do {
            try {
                answerString = RequestMethods.requestingInfoWithYesOrNo("\nDo you want to choose VIP service? (y/n): ");
                break;
            } catch (EmptyInputException | YesOrNoException e) {
                LOGGER_TO_CONSOLE_AND_FILE.error(e.getMessage());
            }
        } while (true);
        if (answerString.equals("y")) {
            return runAddVipServiceMenu(patient);
        } else {
            LOGGER_LN.info("OK!");
            patient.getDoctor().addPatient(patient);
            return runPatientMenu(patient);
        }
    }

    private ConsoleMenu runAddVipServiceMenu(Patient patient) {
        do {
            if (patient.getVipServices().size() < VipService.values().length) {
                int index = 1;
                List<VipService> tempList = new ArrayList<>();
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
                        answer = RequestMethods.requestingInfoWithChoice("Enter number of VIP service to add it: ", index - 1);
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
                    answer = RequestMethods.requestingInfoWithYesOrNo("Do you want to choose another VIP service? (y/n): ");
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
        patient.getDoctor().addPatient(patient);
        return runPatientMenu(patient);
    }

    private ConsoleMenu runDeleteVipServiceMenu(Patient patient) {
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
                        answer = RequestMethods.requestingInfoWithChoice("Enter number of VIP service to delete it: ", index - 1);
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
                    answer = RequestMethods.requestingInfoWithYesOrNo("Do you want to delete another VIP service? (y/n): ");
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
        return runPatientMenu(patient);
    }
}
