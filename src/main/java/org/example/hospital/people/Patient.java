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
    private ArrayList<Service> services = new ArrayList<>();

    public Patient() {
        super();
    }

    public void goToHospital() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nWelcome to '" + Db.hospital.getHospitalTitle() + "'");
        String input;
        do {
            System.out.print("Would you like to register as a patient? (y/n): ");
            input = scanner.nextLine();
        } while (!input.equals("n") && !input.equals("y"));
        if (input.equals("y")) {
            this.register();
        } else {
            System.out.println("Good bye!");
        }
        scanner.close();
    }

    public void register() {
        Scanner scanner = new Scanner(System.in);
        Address address = new Address();

        String strInput;
        int intInput;

        strInput = this.inputString(scanner, "Enter your first name:");
        this.setFirstName(strInput);

        strInput = this.inputString(scanner, "Enter your last name:");
        this.setLastName(strInput);

        intInput = this.inputInt(scanner, "Enter your age:");
        this.setAge(intInput);

        strInput = this.inputString(scanner, "Enter your city:");
        address.city = strInput;

        strInput = this.inputString(scanner, "Enter your street:");
        address.street = strInput;

        intInput = this.inputInt(scanner, "Enter your house number:");
        address.houseNumber = intInput;

        intInput = this.inputInt(scanner, "Enter your flat number:");
        address.flatNumber = intInput;

        this.setAddress(address);
        Db.hospital.addPatient(this);

        intInput = this.chooseInputInt(scanner, "Enter your complaint (Cough - 1, No smells - 2, Broken bone - 3, None - 4):");
        diagnosis = this.getDiagnose(intInput);

        Scanner scanner2 = new Scanner(System.in);
        String input;
        Service service;
        do {
            intInput = this.chooseInputInt(scanner, "Chose the service (Appointment - 1, Treatment - 2, Hospitalization - 3, Nothing - 4):");
            service = getService(intInput);
            services.add(service);
            department.getEmployee(Position.DEPT_HEAD).increaseCostOfServices(service.getPrice());
            department.getEmployee(Position.DOCTOR).increaseCostOfServices(service.getPrice());
            department.getEmployee(Position.NURSE).increaseCostOfServices(service.getPrice());
            do {
                System.out.print("\tDo you want another service? (y/n): ");
                input = scanner.nextLine();
            } while (!input.equals("n") && !input.equals("y"));
            if (input.equals("n")) {
                System.out.println("OK! That's all!");
                break;
            }
        } while(true);
        scanner2.close();

        scanner.close();
    }

    public Diagnosis getDiagnose(int number) {
        switch (number) {
            case (1):
                Db.therapeuticDept.addPatient(this);
                department = Db.therapeuticDept;
                return Diagnosis.FLU;
            case (2):
                Db.therapeuticDept.addPatient(this);
                department = Db.therapeuticDept;
                return Diagnosis.COVID;
            case (3):
                Db.surgeryDept.addPatient(this);
                department = Db.surgeryDept;
                return Diagnosis.BROKEN_BONE;
            default:
                return Diagnosis.HEALTHY;
        }
    }

    public Service getService(int number) {
        switch (number) {
            case (1):
                return Service.APPOINTMENT;
            case (2):
                return Service.TREATMENT;
            case (3):
                return Service.HOSPITALIZATION;
            default:
                return Service.NOTHING;
        }
    }

    private String inputString(Scanner scanner, String text) {
        String input;
        do {
            System.out.print("\t" + text + " ");
            input = scanner.nextLine();
        } while (input.isEmpty());
        return input;
    }

    private int inputInt(Scanner scanner, String text) {
        String input;
        int number = 0;
        do {
            System.out.print("\t" + text + " ");
            input = scanner.nextLine();
            try {
                number = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                input = "";
            }
        } while (input.isEmpty() || number < 0);
        return number;
    }

    private int chooseInputInt(Scanner scanner, String text) {
        String input;
        int number = 0;
        do {
            System.out.print("\t" + text + " ");
            input = scanner.nextLine();
            try {
                number = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                input = "";
            }
        } while (input.isEmpty() || number < 1 || number > 4);
        return number;
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
        return age == that.age;
    }

    @Override
    public String toString() {
        StringBuilder unitingServices = new StringBuilder();
        double sumOfServices = 0;
        for (Service service: services) {
            unitingServices.append("(").append(service.getTitle()).append(") ");
            sumOfServices += service.getPrice();
        }
        try {
            return "\nPatient: (" + this.getRole() + "): " +
                    super.toString() +
                    "\n\tDiagnosis: " + diagnosis +
                    "\n\tDepartment: " + department.getDepartmentTitle() +
                    "\n\tOffice: " + department.getOfficeRoom() +
                    "\n\tTherapist: " + department.getEmployee(Position.DOCTOR).getFirstName() + " " + department.getEmployee(Position.DOCTOR).getLastName() +
                    "\n\tNurse: " + department.getEmployee(Position.NURSE).getFirstName() + " " + department.getEmployee(Position.NURSE).getLastName() +
                    "\n\tServices: " + unitingServices +
                    "\n\tPrice: " + sumOfServices + " BYN";
        } catch (NullPointerException e) {
            return "\nThe patient was not registered";
        }
    }
}
