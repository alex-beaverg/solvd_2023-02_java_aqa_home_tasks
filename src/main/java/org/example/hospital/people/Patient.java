package org.example.hospital.people;

import org.example.hospital.pseudo_database.Db;
import org.example.hospital.structure.Service;
import org.example.hospital.structure.Department;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Patient extends Person {
    private Diagnosis diagnosis;
    private Department department;
    private final ArrayList<Service> services = new ArrayList<>();

    public Patient() {
        super();
    }

    public void goToHospital() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nWelcome to '" + Db.hospital.getHospitalTitle() + "'");
        String answer = requestingInfoWithYesOrNo(scanner, "Would you like to register as a patient? (y/n): ");
        if (answer.equals("y")) {
            register();
        } else {
            System.out.println("OK!");
        }
        scanner.close();
    }

    public void register() {
        Scanner scanner = new Scanner(System.in);
        Address address = new Address();

        setFirstName(requestingInfoString(scanner, "\tEnter your first name: "));
        setLastName(requestingInfoString(scanner, "\tEnter your last name: "));
        setAge(requestingInfoInt(scanner, "\tEnter your age: "));
        address.city = requestingInfoString(scanner, "\tEnter your city: ");
        address.street = requestingInfoString(scanner, "\tEnter your street: ");
        address.houseNumber = requestingInfoInt(scanner, "\tEnter your house number: ");
        address.flatNumber = requestingInfoInt(scanner, "\tEnter your flat number: ");
        setAddress(address);
        Db.hospital.addPatient(this);
        diagnosis = getDiagnose(requestingInfoWithChoice(scanner,
                "\tEnter your complaint (Cough - 1, No smells - 2, Broken bone - 3, Unknown - 4): "));
        department.addPatient(this);
        do {
            Service service = getService(requestingInfoWithChoice(scanner,
                    "\tChoose the service (Appointment - 1, Treatment - 2, Hospitalization - 3, Examination - 4): "));
            services.add(service);
            department.getEmployee(Position.DEPT_HEAD).increaseCostOfServices(service.getPrice());
            department.getEmployee(Position.DOCTOR).increaseCostOfServices(service.getPrice());
            department.getEmployee(Position.NURSE).increaseCostOfServices(service.getPrice());
            String answer = requestingInfoWithYesOrNo(scanner, "\tDo you want to choose another service? (y/n): ");
            if (answer.equals("n")) {
                System.out.println("OK!");
                break;
            }
        } while (true);
        scanner.close();
    }

    private Diagnosis getDiagnose(int number) {
        switch (number) {
            case (1):
                department = Db.therapeuticDept;
                return Diagnosis.FLU;
            case (2):
                department = Db.therapeuticDept;
                return Diagnosis.COVID;
            case (3):
                department = Db.surgeryDept;
                return Diagnosis.BONE_FRACTURE;
            default:
                department = Db.therapeuticDept;
                return Diagnosis.UNKNOWN;
        }
    }

    private Service getService(int number) {
        switch (number) {
            case (1):
                return Service.APPOINTMENT;
            case (2):
                return Service.TREATMENT;
            case (3):
                return Service.HOSPITALIZATION;
            default:
                return Service.EXAMINATION;
        }
    }

    private String requestingInfoWithYesOrNo(Scanner scanner, String text) {
        String answer;
        do {
            System.out.print(text);
            answer = scanner.nextLine();
        } while (!answer.equals("y") && !answer.equals("n"));
        return answer;
    }

    private String requestingInfoString(Scanner scanner, String text) {
        String answer;
        do {
            System.out.print(text);
            answer = scanner.nextLine();
        } while (answer.isEmpty());
        return answer;
    }

    private int requestingInfoInt(Scanner scanner, String text) {
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

    private int requestingInfoWithChoice(Scanner scanner, String text) {
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
        } while (answer.isEmpty() || numberFromAnswer < 1 || numberFromAnswer > 4);
        return numberFromAnswer;
    }

    @Override
    public String getRole() {
        return "Receiving treatment in hospital";
    }

    @Override
    public int hashCode() {
        int result = firstName == null ? 0 : firstName.hashCode();
        result = 31 * result + (lastName == null ? 0 : lastName.hashCode());
        result = 31 * result + age;
        result = 31 * result + (address == null ? 0 : address.hashCode());
        result = 31 * result + (department == null ? 0 : department.hashCode());
        result = 31 * result + (diagnosis == null ? 0 : diagnosis.hashCode());
        result = 31 * result + services.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Patient that = (Patient) obj;
        if (!Objects.equals(firstName, that.firstName)) return false;
        if (!Objects.equals(lastName, that.lastName)) return false;
        if (!Objects.equals(address, that.address)) return false;
        if (!Objects.equals(department, that.department)) return false;
        if (!Objects.equals(diagnosis, that.diagnosis)) return false;
        if (!Objects.equals(services, that.services)) return false;
        return age == that.age;
    }

    @Override
    public String toString() {
        try {
            return "\nPatient: (" + getRole() + "): " +
                    super.toString() +
                    "\n\tDiagnosis: " + diagnosis +
                    "\n\tDepartment: " + department.getDepartmentTitle() +
                    "\n\tOffice: " + department.getOfficeRoom() +
                    "\n\tTherapist: " + department.getEmployee(Position.DOCTOR).getFirstName() + " " + department.getEmployee(Position.DOCTOR).getLastName() +
                    "\n\tNurse: " + department.getEmployee(Position.NURSE).getFirstName() + " " + department.getEmployee(Position.NURSE).getLastName() +
                    "\n\tServices: " + combineServices() +
                    "\n\tPrice: " + calculateServicesPrice() + " BYN";
        } catch (NullPointerException e) {
            return "\nThe patient was not registered";
        }
    }

    private StringBuilder combineServices() {
        StringBuilder combiningServices = new StringBuilder();
        for (Service service: services) {
            combiningServices.append("[").append(service.getTitle()).append("] ");
        }
        return combiningServices;
    }

    private double calculateServicesPrice() {
        double servicesPrice = 0;
        for (Service service: services) {
            servicesPrice += service.getPrice();
        }
        return servicesPrice;
    }
}
