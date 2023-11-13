package org.example.hospital.structure;

import static org.example.hospital.util.LoggerConstants.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.hospital.custom_exceptions.EmptyInputException;
import org.example.hospital.custom_exceptions.MenuItemNumberOutOfBoundsException;
import org.example.hospital.custom_exceptions.YesOrNoException;
import org.example.hospital.people.Employee;
import org.example.hospital.people.Patient;
import org.example.hospital.util.RequestMethods;

import java.util.ArrayList;
import java.util.List;

public class ServicesActions {
    private static final Logger LOGGER_TO_CONSOLE_AND_FILE;

    static {
        LOGGER_TO_CONSOLE_AND_FILE = LogManager.getLogger(ServicesActions.class);
    }

    public static Patient requestForAssignDoctor(Patient patient) {
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
            assignDoctor(patient);
        } else {
            LOGGER_LN.info("Your doctor (" + patient.getDoctor().getFullName() + ") was assigned by hospital automatically");
        }
        return patient;
    }

    private static void assignDoctor(Patient patient) {
        int index = 1;
        LN_LOGGER_LN.info("All available doctors in your department:");
        for (Employee doctor: patient.getDepartment().getEmployeesBySpecialistClass(2)) {
            LOGGER_LN.info("[" + index + "] - " + doctor.getPersonToPrintInList());
            index++;
        }
        int answer;
        do {
            try {
                answer = RequestMethods.requestingInfoWithChoice("Enter number of doctor to choose him: ", index - 1);
                break;
            } catch (EmptyInputException | MenuItemNumberOutOfBoundsException e) {
                LOGGER_TO_CONSOLE_AND_FILE.error(e.getMessage());
            } catch (NumberFormatException e) {
                LOGGER_TO_CONSOLE_AND_FILE.error("[NumberFormatException]: Entered data is not a number!");
            }
        } while (true);
        if (!patient.getDoctor().equals(patient.getDepartment().getEmployeesBySpecialistClass(2).get(answer - 1))) {
            patient.setDoctor(patient.getDepartment().getEmployeesBySpecialistClass(2).get(answer - 1));
        }
        LOGGER_LN.info("Your doctor (" + patient.getDoctor().getFullName() + ") was assigned");
    }

    public static Patient changeDoctor(Patient patient) {
        int index = 1;
        List<Employee> tempList = new ArrayList<>();
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
                answer = RequestMethods.requestingInfoWithChoice("Enter number of doctor to choose him: ", index - 1);
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

    public static Patient addService(Patient patient) {
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
            return addVipServices(patient);
        } else {
            LOGGER_LN.info("OK!");
            patient.getDoctor().addPatient(patient);
            return patient;
        }
    }

    public static Patient addVipServices(Patient patient) {
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
        return patient;
    }

    public static Patient deleteVipServices(Patient patient) {
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
        return patient;
    }
}
