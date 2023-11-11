package org.example.hospital.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.hospital.custom_exceptions.*;
import org.example.hospital.data.Creator;
import org.example.hospital.people.*;
import org.example.hospital.structure.*;
import org.example.hospital.structure.accounting.Accounting;
import org.example.hospital.util.menu_enums.*;

public final class ConsoleMenu {
    private final Hospital hospital;
    public static final Logger LOGGER_LN;
    public static final Logger LN_LOGGER_LN;
    public static final Logger LOGGER_TO_CONSOLE_AND_FILE;

    static {
        LOGGER_LN = LogManager.getLogger("InsteadOfSOUT_ln");
        LN_LOGGER_LN = LogManager.getLogger("ln_InsteadOfSOUT_ln");
        LOGGER_TO_CONSOLE_AND_FILE = LogManager.getLogger(ConsoleMenu.class);
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
                hospital.showDepartments();
                return runMainMenu();
            }
            case (2) -> {
                hospital.showEmployees();
                return runMainMenu();
            }
            case (3) -> {
                hospital.showDiagnosesMap();
                return runMainMenu();
            }
            case (4) -> {
                return runDoctorsMenu();
            }
            case (5) -> {
                return runPatientsMenu();
            }
            default -> {
                return tearDown();
            }
        }
    }

    private ConsoleMenu runDoctorsMenu() {
        int answer = runAnyMenu("Doctors menu:", DoctorsMenu.values());
        switch (answer) {
            case (1) -> {
                return runDoctorMenu(GeneralActions.chooseDoctorFromList(hospital));
            }
            case (2) -> {
                return runMainMenu();
            }
            default -> {
                return tearDown();
            }
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
                patient = GeneralActions.findExistPatient(hospital);
                if (patient != null) {
                    return runPatientMenu(patient);
                } else {
                    return runPatientsMenu();
                }
            }
            case (2) -> {
                patient = GeneralActions.registerNewPatient(hospital);
                return runComplaintsMenu(patient);
            }
            case (3) -> {
                patient = GeneralActions.choosePatient(hospital);
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
                return runPatientMenu(ServicesActions.changeDoctor(patient));
            }
            case (2) -> {
                return runPatientMenu(ServicesActions.deleteVipServices(patient));
            }
            case (3) -> {
                return runPatientMenu(ServicesActions.addVipServices(patient));
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
        patient.setDiagnosis(GeneralActions.getDiagnose(hospital, patient, answer));
        hospital.addPatientToDiagnosesMap(patient);
        LOGGER_LN.info("Diagnosis (" + patient.getDiagnosis().getTitle() + ") was made");
        return runPatientMenu(ServicesActions.addService(ServicesActions.requestForAssignDoctor(patient)));
    }
}
