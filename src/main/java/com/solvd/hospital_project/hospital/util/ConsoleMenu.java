package com.solvd.hospital_project.hospital.util;

import static com.solvd.hospital_project.hospital.util.Printers.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.hospital_project.hospital.custom_exceptions.*;
import com.solvd.hospital_project.hospital.people.*;
import com.solvd.hospital_project.hospital.structure.*;
import com.solvd.hospital_project.hospital.util.menu_enums.*;
import com.solvd.hospital_project.hospital.data.Creator;
import com.solvd.hospital_project.hospital.structure.accounting.Accounting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ConsoleMenu {
    private final Hospital hospital;
    private static final Logger LOGGER;

    static {
        LOGGER = LogManager.getLogger(ConsoleMenu.class);
    }

    {
        hospital = Creator.generateHospital();
    }

    public void runApp() {
        PRINT2LN.info("Welcome to the " + hospital);
        runMainMenu();
    }

    private int runAnyMenu(String title, IMenu[] menuItems) {
        int index = 1;
        PRINT2LN.info(title);
        for (IMenu item : menuItems) {
            PRINTLN.info("[" + index + "] - " + item.getTitle());
            index++;
        }
        int answer;
        do {
            try {
                answer = RequestMethods.requestingInfoWithChoice("Enter the menu item number: ", index - 1);
                break;
            } catch (EmptyInputException | MenuItemOutOfBoundsException e) {
                LOGGER.error(e.getMessage());
            } catch (NumberFormatException e) {
                LOGGER.error("[NumberFormatException]: Entered data is not a number!");
            }
        } while (true);
        return answer;
    }

    private ConsoleMenu tearDown() {
        RequestMethods.closeScanner();
        PRINTLN.info("Good bye!");
        return null;
    }

    private ConsoleMenu runMainMenu() {
        int answer = runAnyMenu("Main menu:", MainMenu.values());
        switch (answer) {
            case (1) -> {
                GeneralActions.showDepartments(hospital);
                return runMainMenu();
            }
            case (2) -> {
                GeneralActions.showEmployees(hospital);
                return runMainMenu();
            }
            case (3) -> {
                GeneralActions.showDiagnosesMap(hospital);
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
                return runDoctorMenu((Employee) GeneralActions.choosePersonFromList("doctor", hospital));
            }
            case (2) -> {
                GeneralActions.showDoctorsWithTheirPatients(hospital);
                return runDoctorsMenu();
            }
            case (3) -> {
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
                PRINT2LN.info(doctor);
                return runDoctorMenu(doctor);
            }
            case (2) -> {
                PRINT2LN.info(Accounting.getPayslip(doctor));
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
                patient = (Patient) GeneralActions.choosePersonFromList("patient", hospital);
                return runPatientMenu(patient);
            }
            case (2) -> {
                patient = GeneralActions.findExistPatient(hospital);
                if (patient != null) {
                    return runPatientMenu(patient);
                } else {
                    return runPatientsMenu();
                }
            }
            case (3) -> {
                patient = GeneralActions.registerNewPatient(hospital);
                return runComplaintsMenu(patient);
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
                PRINT2LN.info(patient);
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
        do {
            int answer;
            Diagnosis diagnosis;
            Map<Integer, Integer> tempMap = new HashMap<>();
            int index = 0;
            if (patient.getDiagnoses().isEmpty()) {
                answer = runAnyMenu("Complaints menu:", ComplaintsMenu.values());
                diagnosis = GeneralActions.getDiagnose(hospital, patient, answer);
            } else {
                List<ComplaintsMenu> reducedComplaintsMenu = new ArrayList<>();
                for (ComplaintsMenu item : ComplaintsMenu.values()) {
                    index++;
                    if (item.getType().equals(patient.getDiagnoses().get(0).getType())) {
                        reducedComplaintsMenu.add(item);
                        tempMap.put(reducedComplaintsMenu.size(), index);
                    }
                }
                answer = runAnyMenu("Complaints menu:", reducedComplaintsMenu.toArray(new IMenu[0]));
                diagnosis = GeneralActions.getDiagnose(hospital, patient, tempMap.get(answer));
            }
            patient.addDiagnosis(diagnosis);
            hospital.addPatientToDiagnosesMap(patient);
            PRINTLN.info("Diagnosis (" + diagnosis.getTitle() + ") was made");
            String answerString;
            do {
                try {
                    answerString = RequestMethods.requestingInfoWithYesOrNo("\nDo you have another complaint? (y/n): ");
                    break;
                } catch (EmptyInputException | YesOrNoException e) {
                    LOGGER.error(e.getMessage());
                }
            } while (true);
            if (answerString.equals("n")) {
                PRINTLN.info("OK!");
                break;
            }
        } while (true);
        return runPatientMenu(ServicesActions.addService(ServicesActions.requestForAssignDoctor(patient)));
    }
}
